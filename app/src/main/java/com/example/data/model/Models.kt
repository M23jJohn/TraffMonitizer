package com.example.data.model

enum class NodeStatus {
  STOPPED,
  CONNECTING,
  ONLINE,
  PAUSED_BATTERY,
  PAUSED_WIFI,
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

data class TrafficStats(
  val currentDownloadBps: Long = 0L,
  val currentUploadBps: Long = 0L,
  val todayBytes: Long = 0L,
  val lifetimeBytes: Long = 0L,
  val totalRequestsServed: Int = 0,
  val estimatedEarningsUsd: Double = 0.0,
  val sessionDurationSeconds: Long = 0L
)

data class NetworkInfo(
  val ipAddress: String = "Detecting...",
  val isp: String = "Detecting...",
  val country: String = "Global",
  val countryCode: String = "US",
  val latencyMs: Int = 0,
  val networkType: String = "Wi-Fi",
  val isWifi: Boolean = true,
  val isCharging: Boolean = true,
  val natType: String = "Moderate / Symmetric"
)

data class AppSettings(
  val token: String = "",
  val deviceName: String = "Android-Node",
  val autoStartOnBoot: Boolean = false,
  val wifiOnly: Boolean = false,
  val chargingOnly: Boolean = false,
  val wakeLockEnabled: Boolean = true,
  val dailyLimitMb: Int = 0, // 0 = unlimited
  val earningRatePerGb: Double = 0.20 // $0.20 per GB average for residential proxy
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
