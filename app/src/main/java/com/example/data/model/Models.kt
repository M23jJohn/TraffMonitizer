package com.example.data.model

/**
 * UI-facing node state. Each value maps to a stage the node has actually
 * reached in [com.example.node.NodeConnectionStatus] — none of them is set
 * merely because the Android service started.
 */
enum class NodeStatus {
  /** Node not running. */
  STOPPED,

  /** Resolving the load balancer, opening the socket, or awaiting HELLO_RESPONSE. */
  CONNECTING,

  /** HELLO_RESPONSE accepted: registered with the backend and serving traffic. */
  ONLINE,

  /** Held off by the charging-only policy. */
  PAUSED_BATTERY,

  /** Held off by the Wi-Fi-only policy, or no usable network. */
  PAUSED_WIFI,

  /** A real failure occurred (resolve, connect, or rejected handshake). */
  ERROR
}

enum class LogLevel {
  INFO,
  DATA,
  SUCCESS,
  WARN,
  ERROR
}

data class LogEntry(
  val id: Long = System.nanoTime(),
  val timestamp: String,
  val level: LogLevel,
  val message: String,
  val tag: String = "CLI_V2"
)

/**
 * Traffic figures shown in the UI.
 *
 * Everything here is either measured from bytes this node actually relayed
 * (the session/throughput fields) or reported by the TraffMonetizer backend in
 * STAT_RESPONSE / BALANCE_RESPONSE (the total/balance fields). Nothing is
 * synthesized. Nullable fields mean "the backend has not told us yet" and must
 * be rendered as an unavailable state rather than as zero.
 */
data class TrafficStats(
  /** Measured relay throughput over the last sample window, bytes/sec. */
  val currentDownloadBps: Long = 0L,
  val currentUploadBps: Long = 0L,
  /** Bytes relayed since this session started, counted at the socket. */
  val sessionInboundBytes: Long = 0L,
  val sessionOutboundBytes: Long = 0L,
  /** Server-reported lifetime counters; null until a STAT_RESPONSE arrives. */
  val totalInboundBytes: Long? = null,
  val totalOutboundBytes: Long? = null,
  val totalRequestsServed: Long? = null,
  /** Server-reported account balance in USD; null until a BALANCE_RESPONSE arrives. */
  val balanceUsd: Double? = null,
  val last30DaysUsd: Double? = null,
  val sessionDurationSeconds: Long = 0L
) {
  /** Bytes relayed this session, both directions. */
  val sessionTotalBytes: Long get() = sessionInboundBytes + sessionOutboundBytes
}

/**
 * Observed network conditions.
 *
 * [ipAddress], [isp] and [latencyMs] are null until actually measured — the app
 * shows a placeholder rather than inventing a plausible-looking value. NAT type
 * and geolocation are not exposed by the node protocol, so they are not modelled.
 */
data class NetworkInfo(
  val ipAddress: String? = null,
  val isp: String? = null,
  val latencyMs: Int? = null,
  val networkType: String = "Unknown",
  val isWifi: Boolean = false,
  val isConnected: Boolean = false,
  val isCharging: Boolean = false
)

data class AppSettings(
  val token: String = "",
  val deviceName: String = "Android-Node",
  val autoStartOnBoot: Boolean = false,
  val wifiOnly: Boolean = false,
  val chargingOnly: Boolean = false,
  val wakeLockEnabled: Boolean = true,
  val dailyLimitMb: Int = 0 // 0 = unlimited
)

data class RemoteNode(
  val id: Long = 0L,
  val name: String,
  val ip: String,
  val platform: String, // "Docker x86_64", "Docker ARM64", "Android Node", "VPS Linux"
  val isOnline: Boolean,
  val totalTrafficMb: Double,
  val estimatedEarningsUsd: Double,
  val lastSeenTimestamp: Long = System.currentTimeMillis()
)
