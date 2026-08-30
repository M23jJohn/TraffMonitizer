package com.example.node

/**
 * Node configuration, mirroring the reference SDK's `com.tm.C0148o`
 * (`com.traffmonetizer.sdk.repository.config.Config`).
 *
 * Production constants verified against the cli_v2 client binary (v1.3.3):
 *   balancerHost = "blnc.traffmonetizer.com", apiPort = 769, sdkVersion = "1.3.3"
 */
internal data class NodeConfig(
  /** User-supplied application token. Never logged, never persisted outside SharedPreferences. */
  val token: String,
  /** 16-byte device identity: a UUID derived from Settings.Secure.ANDROID_ID. */
  val instanceId: ByteArray,
  val wifiOnly: Boolean,
  /** Optional device alias sent via SET_NAME_MESSAGE; blank means "don't set a name". */
  val deviceName: String = "",
  val balancerHost: String = BALANCER_HOST,
  val apiPort: Int = API_PORT,
  /** Non-blank pins the node to a fixed server, skipping load-balancer resolution. */
  val serverHost: String = "",
  val sdkVersion: String = SDK_VERSION,
) {
  /**
   * Version string sent in HELLO. cli_v2 sends the bare version — the handshake
   * builds `String("1.3.3")` with no suffix (0x5530d) — so we do too.
   */
  val helloVersion: String
    get() = sdkVersion

  // Generated equals/hashCode would compare instanceId by reference.
  override fun equals(other: Any?): Boolean {
    if (this === other) return true
    if (other !is NodeConfig) return false
    return token == other.token &&
      instanceId.contentEquals(other.instanceId) &&
      wifiOnly == other.wifiOnly &&
      deviceName == other.deviceName &&
      balancerHost == other.balancerHost &&
      apiPort == other.apiPort &&
      serverHost == other.serverHost &&
      sdkVersion == other.sdkVersion
  }

  override fun hashCode(): Int {
    var result = token.hashCode()
    result = 31 * result + instanceId.contentHashCode()
    result = 31 * result + wifiOnly.hashCode()
    result = 31 * result + deviceName.hashCode()
    result = 31 * result + balancerHost.hashCode()
    result = 31 * result + apiPort
    result = 31 * result + serverHost.hashCode()
    result = 31 * result + sdkVersion.hashCode()
    return result
  }

  /** Redacted: [toString] must never expose the token. */
  override fun toString(): String =
    "NodeConfig(token=<redacted>, wifiOnly=$wifiOnly, balancer=$balancerHost, port=$apiPort, " +
      "serverHost=${serverHost.ifBlank { "<resolve>" }}, version=$helloVersion)"

  companion object {
    const val BALANCER_HOST = "blnc.traffmonetizer.com"
    const val API_PORT = 769
    const val SDK_VERSION = "1.3.3"

    /** deviceType value the reference SDK sends in HELLO (com.tm.O0). */
    const val DEVICE_TYPE_ANDROID = 3
  }
}
