package com.example.data.preferences

import android.content.Context
import android.content.SharedPreferences
import android.os.Build
import com.example.data.model.AppSettings
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class PreferenceManager(context: Context) {
  private val prefs: SharedPreferences =
    context.getSharedPreferences("traff_monetizer_prefs", Context.MODE_PRIVATE)

  private val _settingsFlow = MutableStateFlow(loadSettings())
  val settingsFlow: StateFlow<AppSettings> = _settingsFlow.asStateFlow()

  fun loadSettings(): AppSettings {
    val defaultDevice = "Android-${Build.MODEL.take(12).replace(" ", "-")}"
    return AppSettings(
      token = prefs.getString(KEY_TOKEN, "") ?: "",
      deviceName = prefs.getString(KEY_DEVICE_NAME, defaultDevice) ?: defaultDevice,
      autoStartOnBoot = prefs.getBoolean(KEY_AUTO_START, false),
      wifiOnly = prefs.getBoolean(KEY_WIFI_ONLY, false),
      chargingOnly = prefs.getBoolean(KEY_CHARGING_ONLY, false),
      wakeLockEnabled = prefs.getBoolean(KEY_WAKE_LOCK, true),
      dailyLimitMb = prefs.getInt(KEY_DAILY_LIMIT, 0)
    )
  }

  fun saveSettings(settings: AppSettings) {
    prefs.edit()
      .putString(KEY_TOKEN, settings.token)
      .putString(KEY_DEVICE_NAME, settings.deviceName)
      .putBoolean(KEY_AUTO_START, settings.autoStartOnBoot)
      .putBoolean(KEY_WIFI_ONLY, settings.wifiOnly)
      .putBoolean(KEY_CHARGING_ONLY, settings.chargingOnly)
      .putBoolean(KEY_WAKE_LOCK, settings.wakeLockEnabled)
      .putInt(KEY_DAILY_LIMIT, settings.dailyLimitMb)
      .apply()
    _settingsFlow.value = settings
  }

  fun updateToken(token: String) {
    val current = _settingsFlow.value
    saveSettings(current.copy(token = token.trim()))
  }

  fun updateDeviceName(name: String) {
    val current = _settingsFlow.value
    saveSettings(current.copy(deviceName = name.trim()))
  }

  companion object {
    private const val KEY_TOKEN = "key_token"
    private const val KEY_DEVICE_NAME = "key_device_name"
    private const val KEY_AUTO_START = "key_auto_start"
    private const val KEY_WIFI_ONLY = "key_wifi_only"
    private const val KEY_CHARGING_ONLY = "key_charging_only"
    private const val KEY_WAKE_LOCK = "key_wake_lock"
    private const val KEY_DAILY_LIMIT = "key_daily_limit"
  }
}
