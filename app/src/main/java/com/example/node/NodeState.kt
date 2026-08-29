package com.example.node

/**
 * Connection lifecycle, mirroring the reference SDK's public `ConnectionStatus`
 * hierarchy (`com.traffmonetizer.sdk.model.*`).
 *
 * Each value corresponds to a stage that has *actually* been reached — nothing
 * here is set optimistically.
 */
internal sealed interface NodeConnectionStatus {
  /** Not started, or stopped cleanly. */
  data object Disconnected : NodeConnectionStatus

  /** Asking the load balancer which edge server to use. */
  data object ResolvingLoadBalancer : NodeConnectionStatus

  /** TCP connect to the resolved server is in progress. */
  data object SocketConnecting : NodeConnectionStatus

  /** Socket is open; HELLO sent, waiting for HELLO_RESPONSE. */
  data object Authenticating : NodeConnectionStatus

  /** HELLO_RESPONSE received with status=true. The node is registered and serving. */
  data class Connected(val serverHost: String, val statusMessage: String) : NodeConnectionStatus

  /** A stage failed. [reason] is the real cause, suitable for display. */
  data class Failed(val reason: String) : NodeConnectionStatus

  /** Held off deliberately: no network, or wifiOnly/chargingOnly policy not satisfied. */
  data class Paused(val reason: PauseReason) : NodeConnectionStatus
}

internal enum class PauseReason {
  NO_NETWORK,
  WIFI_ONLY_POLICY,
  CHARGING_ONLY_POLICY,
  DAILY_LIMIT_REACHED,
}

/**
 * Traffic counters as reported by the TraffMonetizer backend in STAT_RESPONSE.
 *
 * The reference SDK's `StatsInfo` (`com.tm.C0179s3`) carries exactly these three
 * longs and they originate server-side — this client does not synthesize them.
 */
internal data class NodeStats(
  val inboundTraffic: Long,
  val outboundTraffic: Long,
  val requestsCount: Long,
)

/**
 * Account balance as reported by the backend in BALANCE_RESPONSE.
 *
 * The reference SDK's `BalanceInfo` (`com.tm.C0106i`) carries these two doubles.
 * There is no client-side earnings formula anywhere in the reference SDK, so the
 * app must show this value or show nothing.
 */
internal data class NodeBalance(
  val balance: Double,
  val last30Days: Double,
)
