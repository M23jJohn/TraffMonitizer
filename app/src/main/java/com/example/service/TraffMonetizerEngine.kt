package com.example.service

import android.content.Context
import com.example.data.model.AppSettings
import com.example.data.model.LogEntry
import com.example.data.model.LogLevel
import com.example.data.model.NetworkInfo
import com.example.data.model.NodeStatus
import com.example.data.model.TrafficStats
import com.example.data.repository.TraffRepository
import com.example.node.ConnectivityMonitor
import com.example.node.DeviceIdentity
import com.example.node.NodeConfig
import com.example.node.NodeManager
import com.example.node.PauseReason
import com.example.node.TmConnection
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch

/**
 * UI-side facade over the real TraffMonetizer node client ([NodeManager]).
 *
 * It owns no traffic logic — every status transition and every counter is
 * forwarded from the actual connection. Nothing here runs a simulation, and no
 * log line is emitted unless the corresponding operation really happened.
 */
object TraffMonetizerEngine {

  private val engineScope = CoroutineScope(SupervisorJob() + Dispatchers.Default)
  private var manager: NodeManager? = null
  private var repository: TraffRepository? = null
  private var sessionStartAt = 0L

  private val _status = MutableStateFlow(NodeStatus.STOPPED)
  val status: StateFlow<NodeStatus> = _status

  private val _stats = MutableStateFlow(TrafficStats())
  val stats: StateFlow<TrafficStats> = _stats

  private val _networkInfo = MutableStateFlow(NetworkInfo())
  val networkInfo: StateFlow<NetworkInfo> = _networkInfo

  private val _logs = MutableStateFlow<List<LogEntry>>(emptyList())
  val logs: StateFlow<List<LogEntry>> = _logs

  private val timeFormat = SimpleDateFormat("HH:mm:ss", Locale.US)

  fun init(context: Context) {
    if (repository == null) {
      repository = TraffRepository(context.applicationContext)
    }
    if (manager == null) {
      manager = NodeManager(context.applicationContext) { level, message, tag ->
        appendLog(level.toAppLevel(), message, tag)
      }
      observeManager()
    }
    updateNetworkInfo(context)
  }

  private fun TmConnection.LogLevel.toAppLevel(): LogLevel = when (this) {
    TmConnection.LogLevel.INFO -> LogLevel.INFO
    TmConnection.LogLevel.DATA -> LogLevel.DATA
    TmConnection.LogLevel.SUCCESS -> LogLevel.SUCCESS
    TmConnection.LogLevel.WARN -> LogLevel.WARN
    TmConnection.LogLevel.ERROR -> LogLevel.ERROR
  }

  /** Bridges the real client's status/stat flows into the UI models, truthfully. */
  private fun observeManager() {
    val mgr = manager ?: return
    engineScope.launch {
      mgr.status.collect { connectionStatus ->
        _status.value = connectionStatus.toNodeStatus()
      }
    }
    engineScope.launch {
      mgr.stats.collect { serverStats ->
        _stats.value = _stats.value.copy(
          totalInboundBytes = serverStats.inboundTraffic,
          totalOutboundBytes = serverStats.outboundTraffic,
          totalRequestsServed = serverStats.requestsCount,
        )
      }
    }
    engineScope.launch {
      mgr.balance.collect { balance ->
        _stats.value = _stats.value.copy(
          balanceUsd = balance?.balance,
          last30DaysUsd = balance?.last30Days,
        )
      }
    }
    engineScope.launch {
      mgr.throughput.collect { tp ->
        _stats.value = _stats.value.copy(
          currentDownloadBps = tp.downloadBps,
          currentUploadBps = tp.outboundBps,
          sessionInboundBytes = mgr.trafficCounter.inboundBytes(),
          sessionOutboundBytes = mgr.trafficCounter.outboundBytes(),
          sessionDurationSeconds = if (sessionStartAt > 0) (System.currentTimeMillis() - sessionStartAt) / 1000 else 0,
        )
      }
    }
  }

  private fun com.example.node.NodeConnectionStatus.toNodeStatus(): NodeStatus = when (this) {
    com.example.node.NodeConnectionStatus.Disconnected -> NodeStatus.STOPPED
    com.example.node.NodeConnectionStatus.ResolvingLoadBalancer,
    com.example.node.NodeConnectionStatus.SocketConnecting,
    com.example.node.NodeConnectionStatus.Authenticating -> NodeStatus.CONNECTING

    is com.example.node.NodeConnectionStatus.Connected -> NodeStatus.ONLINE

    is com.example.node.NodeConnectionStatus.Failed -> NodeStatus.ERROR

    is com.example.node.NodeConnectionStatus.Paused -> when (reason) {
      PauseReason.WIFI_ONLY_POLICY, PauseReason.NO_NETWORK -> NodeStatus.PAUSED_WIFI
      PauseReason.CHARGING_ONLY_POLICY -> NodeStatus.PAUSED_BATTERY
      PauseReason.DAILY_LIMIT_REACHED -> NodeStatus.STOPPED
    }
  }

  fun appendLog(level: LogLevel, message: String, tag: String = "NODE") {
    val entry = LogEntry(
      timestamp = timeFormat.format(Date()),
      level = level,
      message = message,
      tag = tag
    )
    _logs.value = (_logs.value + entry).takeLast(400)
  }

  fun clearLogs() {
    _logs.value = emptyList()
  }

  /**
   * Refreshes observed network conditions. Only reports what is actually
   * measurable; anything unknown stays null and is rendered as unavailable.
   */
  fun updateNetworkInfo(context: Context) {
    engineScope.launch(Dispatchers.IO) {
      val monitor = ConnectivityMonitor(context.applicationContext)
      val net = monitor.current()
      val bm = context.getSystemService(Context.BATTERY_SERVICE) as? android.os.BatteryManager

      var ip: String? = null
      var latency: Int? = null
      if (net.connected) {
        try {
          val start = System.currentTimeMillis()
          val url = java.net.URL("https://api.ipify.org?format=text")
          val conn = url.openConnection() as java.net.HttpURLConnection
          conn.connectTimeout = 5_000
          conn.readTimeout = 5_000
          val body = conn.inputStream.bufferedReader().readText().trim()
          conn.disconnect()
          if (body.isNotEmpty()) {
            ip = body
            latency = (System.currentTimeMillis() - start).toInt().coerceAtLeast(1)
          }
        } catch (_: Exception) {
          // Measurement unavailable; leave null rather than fabricate a value.
        }
      }

      _networkInfo.value = NetworkInfo(
        ipAddress = ip,
        isp = null, // no API we call exposes the ISP name; shown as unavailable
        latencyMs = latency,
        networkType = when {
          net.connected && net.wifi -> "Wi-Fi"
          net.connected -> "Cellular/Ethernet"
          else -> "Disconnected"
        },
        isWifi = net.wifi,
        isConnected = net.connected,
        isCharging = bm?.isCharging ?: false
      )
    }
  }

  /**
   * Starts the real node: builds the config from user settings (token included,
   * never logged) and hands it to [NodeManager].
   */
  fun startNode(context: Context, token: String, deviceName: String, settings: AppSettings) {
    init(context)

    if (token.isBlank()) {
      appendLog(LogLevel.ERROR, "Cannot start node: Application Token is empty. Add it in Settings.", "NODE")
      _status.value = NodeStatus.ERROR
      return
    }

    val instanceId = DeviceIdentity.instanceId(context)
    if (instanceId == null) {
      appendLog(LogLevel.ERROR, "Cannot start node: ANDROID_ID is unavailable on this device.", "NODE")
      _status.value = NodeStatus.ERROR
      return
    }

    val config = NodeConfig(
      token = token,
      instanceId = instanceId,
      wifiOnly = settings.wifiOnly,
      deviceName = deviceName,
      appVersion = "1.0",
    )

    appendLog(LogLevel.INFO, "Starting TraffMonetizer node (SDK-compatible client ${NodeConfig.SDK_VERSION}).", "NODE")
    appendLog(LogLevel.INFO, "Device alias: $deviceName", "NODE")

    sessionStartAt = System.currentTimeMillis()
    val mgr = manager ?: return
    mgr.applyPolicies(
      chargingOnly = settings.chargingOnly,
      dailyLimitMb = settings.dailyLimitMb,
    )
    if (mgr.start(config, settings.wakeLockEnabled)) {
      _status.value = NodeStatus.CONNECTING
      mgr.startStatsPolling()
    } else {
      _status.value = NodeStatus.ERROR
    }
  }

  fun stopNode(context: Context, reason: String = "User stopped node") {
    val mgr = manager
    if (mgr?.isRunning() != true) {
      _status.value = NodeStatus.STOPPED
    } else {
      val sessionBytes = mgr.sessionBytes()
      val startTime = sessionStartAt
      mgr.stop(reason)
      _status.value = NodeStatus.STOPPED
      appendLog(LogLevel.WARN, "Node stopped ($reason). Session relayed ${formatBytes(sessionBytes)}.", "NODE")
      engineScope.launch {
        repository?.recordSession(
          startTime = startTime,
          endTime = System.currentTimeMillis(),
          bytesTransferred = sessionBytes,
          earningsUsd = 0.0 // earnings are server-reported; no local formula is invented
        )
      }
    }
    TraffMonetizerService.stop(context)
  }

  // ---- formatting helpers used by the UI ----

  fun formatBytes(bytes: Long): String {
    if (bytes < 1024) return "$bytes B"
    val kb = bytes / 1024.0
    if (kb < 1024) return String.format(Locale.US, "%.1f KB", kb)
    val mb = kb / 1024.0
    if (mb < 1024) return String.format(Locale.US, "%.2f MB", mb)
    val gb = mb / 1024.0
    return String.format(Locale.US, "%.3f GB", gb)
  }

  fun formatSpeed(bps: Long): String {
    val kbps = bps / 1024.0
    if (kbps < 1000) return String.format(Locale.US, "%.0f KB/s", kbps)
    val mbps = kbps / 1024.0
    return String.format(Locale.US, "%.2f MB/s", mbps)
  }

  fun formatDuration(seconds: Long): String {
    val hrs = seconds / 3600
    val mins = (seconds % 3600) / 60
    val secs = seconds % 60
    return if (hrs > 0) {
      String.format(Locale.US, "%02d:%02d:%02d", hrs, mins, secs)
    } else {
      String.format(Locale.US, "%02d:%02d", mins, secs)
    }
  }
}
