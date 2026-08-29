package com.example.node

/**
 * Node configuration, mirroring the reference SDK's `com.tm.C0148o`
 * (`com.traffmonetizer.sdk.repository.config.Config`).
 *
 * Production constants come from `com.tm.AbstractC0162q`:
 *   balancerHost = "blnc.traffmonetizer.com", apiPort = 769, sdkVersion = "1.2.11"
 * The reference also carries a development profile (srv.traffmonetizer.com:8869)
 * which this app does not use.
 */
internal data class NodeConfig(
  /** User-supplied application token. Never logged, never persisted outside SharedPreferences. */
  val token: String,
  /** 16-byte device identity, MD5 of Settings.Secure.ANDROID_ID (reference: com.tm.C0155p). */
  val instanceId: ByteArray,
  val wifiOnly: Boolean,
  /** Optional device alias sent via SET_NAME_MESSAGE; blank means "don't set a name". */
  val deviceName: String = "",
  val balancerHost: String = BALANCER_HOST,
  val apiPort: Int = API_PORT,
  /** Non-blank pins the node to a fixed server, skipping load-balancer resolution. */
  val serverHost: String = "",
  val sdkVersion: String = SDK_VERSION,
  val appVersion: String = "",
) {
  /**
   * Version string sent in HELLO. The reference (`com.tm.C5`) builds
   * `"<sdkVersion>/sdk"` for bare-SDK builds and `"<sdkVersion>/app/<appVersion>"`
   * when an app version is supplied.
   */
  val helloVersion: String
    get() = if (appVersion.isBlank()) "$sdkVersion/sdk" else "$sdkVersion/app/$appVersion"

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
      sdkVersion == other.sdkVersion &&
      appVersion == other.appVersion
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
    result = 31 * result + appVersion.hashCode()
    return result
  }

  /** Redacted: [toString] must never expose the token. */
  override fun toString(): String =
    "NodeConfig(token=<redacted>, wifiOnly=$wifiOnly, balancer=$balancerHost, port=$apiPort, " +
      "serverHost=${serverHost.ifBlank { "<resolve>" }}, version=$helloVersion)"

  companion object {
    const val BALANCER_HOST = "blnc.traffmonetizer.com"
    const val API_PORT = 769
    const val SDK_VERSION = "1.2.11"

    /** deviceType value the reference SDK sends in HELLO (com.tm.O0). */
    const val DEVICE_TYPE_ANDROID = 3
  }
}
