package com.example.node

import android.content.Context
import android.os.BatteryManager
import android.os.PowerManager
import com.example.node.protocol.InboundMessage
import com.example.node.protocol.MessageType
import com.example.node.protocol.OutboundMessages
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch

/**
 * Drives the TraffMonetizer node lifecycle.
 *
 * Responsibilities, mirroring the reference SDK's bloc wiring (`com.tm.C0095g2`):
 *  - connectivity + policy gating (wifiOnly / chargingOnly) before connecting
 *  - connect -> HELLO -> serve, with a 10 s retry cadence while the goal is "run"
 *  - periodic STAT/BALANCE requests so the UI shows server-reported figures
 *  - truthful logs: a stage is only announced once it has actually happened
 */
internal class NodeManager(
  context: Context,
  private val onLog: (TmConnection.LogLevel, String, String) -> Unit,
) {
  private val appContext = context.applicationContext
  private val connectivity = ConnectivityMonitor(appContext)
  private val counter = TrafficCounter()

  private val _status = MutableStateFlow<NodeConnectionStatus>(NodeConnectionStatus.Disconnected)
  val status: StateFlow<NodeConnectionStatus> = _status.asStateFlow()

  private val _stats = MutableStateFlow(NodeStats(0, 0, 0))
  val stats: StateFlow<NodeStats> = _stats.asStateFlow()

  private val _balance = MutableStateFlow<NodeBalance?>(null)
  val balance: StateFlow<NodeBalance?> = _balance.asStateFlow()

  private val _throughput = MutableStateFlow(TrafficCounter.Throughput(0, 0))
  val throughput: StateFlow<TrafficCounter.Throughput> = _throughput.asStateFlow()

  private val managerScope = CoroutineScope(SupervisorJob() + Dispatchers.Default)
  private var runnerJob: Job? = null
  private var config: NodeConfig? = null
  private var wakeLock: PowerManager.WakeLock? = null

  var sessionStartMillis: Long = 0
    private set

  val trafficCounter: TrafficCounter get() = counter

  fun isRunning(): Boolean = runnerJob?.isActive == true

  /**
   * Starts the node. Idempotent while a session is already in flight.
   * @return false when startup is refused (missing token or device id).
   */
  fun start(config: NodeConfig, keepAwake: Boolean): Boolean {
    if (isRunning()) return true
    if (config.token.isBlank()) {
      onLog(TmConnection.LogLevel.ERROR, "Cannot start: no application token configured.", "NODE")
      return false
    }
    this.config = config
    this.keepAwake = keepAwake
    this.dailyLimitMb = 0
    this.chargingOnly = false
    counter.reset()
    sessionStartMillis = System.currentTimeMillis()
    _balance.value = null
    _stats.value = NodeStats(0, 0, 0)

    if (keepAwake) acquireWakeLock()

    runnerJob = managerScope.launch { superviseLoop(config) }
    return true
  }

  /** Applies app-level policies (charging-only, daily cap) to the running node. */
  fun applyPolicies(chargingOnly: Boolean, dailyLimitMb: Int) {
    this.chargingOnly = chargingOnly
    this.dailyLimitMb = dailyLimitMb
  }

  /** Stops the node; sends BYE before tearing down when a connection is live. */
  fun stop(reason: String) {
    val job = runnerJob
    runnerJob = null
    // The runner performs the BYE + socket close in its own teardown.
    if (job != null) {
      managerScope.launch {
        activeConnection?.shutdown()
        job.cancel()
      }
    }
    releaseWakeLock()
    _status.value = NodeConnectionStatus.Disconnected
    onLog(TmConnection.LogLevel.WARN, "Node stopped ($reason).", "NODE")
  }

  fun shutdownScope() {
    managerScope.cancel()
    releaseWakeLock()
  }

  /** Session bytes this node actually relayed (both directions, counted at the sockets). */
  fun sessionBytes(): Long = counter.inboundBytes() + counter.outboundBytes()

  private suspend fun superviseLoop(config: NodeConfig) {
    var backoffMs = RETRY_DELAY_MS
    while (managerScope.isActive) {
      val net = connectivity.current()

      // Policy gate before any connection attempt.
      val pauseReason = when {
        !net.connected -> PauseReason.NO_NETWORK
        config.wifiOnly && !net.wifi -> PauseReason.WIFI_ONLY_POLICY
        chargingOnly && !isCharging() -> PauseReason.CHARGING_ONLY_POLICY
        dailyLimitMb > 0 && sessionBytes() / (1024L * 1024L) >= dailyLimitMb -> PauseReason.DAILY_LIMIT_REACHED
        else -> null
      }
      if (pauseReason != null) {
        _status.value = NodeConnectionStatus.Paused(pauseReason)
        delay(RETRY_DELAY_MS)
        continue
      }

      val connection = TmConnection(
        config = config,
        scope = managerScope,
        onStatus = { status -> _status.value = status },
        onLog = onLog,
        trafficCounter = counter,
      )
      activeConnection = connection
      _status.value = NodeConnectionStatus.ResolvingLoadBalancer
      onLog(TmConnection.LogLevel.INFO, "Resolving TraffMonetizer load balancer (${config.balancerHost})...", "RESOLVE")

      val result = connection.connectAndServe()
      activeConnection = null

      when {
        result.isSuccess -> {
          _status.value = NodeConnectionStatus.Disconnected
          onLog(TmConnection.LogLevel.WARN, "Connection closed. Retrying in ${RETRY_DELAY_MS / 1000}s...", "NODE")
        }

        result.exceptionOrNull() is CancellationException -> return

        else -> {
          val cause = result.exceptionOrNull()
          _status.value = NodeConnectionStatus.Failed(cause?.message ?: "unknown failure")
          onLog(
            TmConnection.LogLevel.ERROR,
            "Connection failed: ${cause?.message ?: cause?.javaClass?.simpleName ?: "unknown"}. Retrying in ${backoffMs / 1000}s...",
            "NODE"
          )
        }
      }

      delay(backoffMs)
      backoffMs = (backoffMs * 2).coerceAtMost(MAX_RETRY_DELAY_MS)
    }
  }

  // ---- Stats polling (runs only while a connection is live) ----

  fun startStatsPolling() {
    managerScope.launch {
      while (managerScope.isActive) {
        delay(STAT_POLL_INTERVAL_MS)
        val connection = activeConnection ?: continue
        if (!connection.isAliveForPolling()) continue
        counter.sampleThroughput(System.currentTimeMillis()).let { _throughput.value = it }

        val statReply = connection.request(
          OutboundMessages.statRequest(configForPolling().instanceId),
          MessageType.STAT_RESPONSE,
          REQUEST_TIMEOUT_MS
        )
        if (statReply is InboundMessage.StatResponse && statReply.success) {
          _stats.value = NodeStats(
            inboundTraffic = statReply.inboundTraffic,
            outboundTraffic = statReply.outboundTraffic,
            requestsCount = statReply.requestsCount,
          )
        }

        val balanceReply = connection.request(
          OutboundMessages.balanceRequest(configForPolling().instanceId),
          MessageType.BALANCE_RESPONSE,
          REQUEST_TIMEOUT_MS
        )
        if (balanceReply is InboundMessage.BalanceResponse && balanceReply.success) {
          _balance.value = NodeBalance(balanceReply.balance, balanceReply.last30Days)
        }
      }
    }
  }

  private fun configForPolling(): NodeConfig =
    config ?: NodeConfig(token = "", instanceId = ByteArray(16), wifiOnly = false)

  private fun TmConnection.isAliveForPolling(): Boolean = this@NodeManager.runnerJob?.isActive == true

  // ---- Power ----

  private fun acquireWakeLock() {
    if (wakeLock?.isHeld == true) return
    runCatching {
      val pm = appContext.getSystemService(Context.POWER_SERVICE) as PowerManager
      wakeLock = pm.newWakeLock(PowerManager.PARTIAL_WAKE_LOCK, "TraffMonetizer::NodeWakeLock").apply {
        setReferenceCounted(false)
        acquire(WAKE_LOCK_TIMEOUT_MS)
      }
    }
  }

  private fun releaseWakeLock() {
    runCatching { if (wakeLock?.isHeld == true) wakeLock?.release() }
    wakeLock = null
  }

  private fun isCharging(): Boolean {
    val bm = appContext.getSystemService(Context.BATTERY_SERVICE) as? BatteryManager ?: return false
    return bm.isCharging
  }

  private var activeConnection: TmConnection? = null
  @Volatile private var chargingOnly = false
  @Volatile private var dailyLimitMb = 0
  @Volatile private var keepAwake = false

  companion object {
    /** Reference: connection bloc retries after a 10 s delay (com.tm.W). */
    const val RETRY_DELAY_MS = 10_000L
    const val MAX_RETRY_DELAY_MS = 80_000L

    /** Stats/balance refresh cadence; the reference exposes getStats()/getBalance() on demand. */
    const val STAT_POLL_INTERVAL_MS = 60_000L
    const val REQUEST_TIMEOUT_MS = 10_000L
    const val WAKE_LOCK_TIMEOUT_MS = 12 * 60 * 60 * 1000L
  }
}
