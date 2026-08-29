package com.example.ui

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.local.RemoteNodeEntity
import com.example.data.model.AppSettings
import com.example.data.model.LogEntry
import com.example.data.model.LogLevel
import com.example.data.model.NetworkInfo
import com.example.data.model.NodeStatus
import com.example.data.model.TrafficStats
import com.example.data.repository.TraffRepository
import com.example.service.TraffMonetizerEngine
import com.example.service.TraffMonetizerService
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class MainViewModel(application: Application) : AndroidViewModel(application) {
  private val repository = TraffRepository(application)

  val status: StateFlow<NodeStatus> = TraffMonetizerEngine.status
  val stats: StateFlow<TrafficStats> = TraffMonetizerEngine.stats
  val networkInfo: StateFlow<NetworkInfo> = TraffMonetizerEngine.networkInfo
  val logs: StateFlow<List<LogEntry>> = TraffMonetizerEngine.logs

  val settings: StateFlow<AppSettings> = repository.settingsFlow
  val remoteNodes: StateFlow<List<RemoteNodeEntity>> = repository.remoteNodes
    .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

  val lifetimeEarnings: StateFlow<Double?> = repository.totalLifetimeEarnings
    .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), 0.0)

  val lifetimeBytes: StateFlow<Long?> = repository.totalLifetimeBytes
    .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), 0L)

  init {
    TraffMonetizerEngine.init(application)
    viewModelScope.launch {
      val existing = repository.getSettings()
      if (existing.deviceName.isBlank()) {
        repository.updateDeviceName("Android-${android.os.Build.MODEL.take(10).replace(" ", "-")}")
      }
    }
  }

  fun toggleNode() {
    val currentStatus = status.value
    if (currentStatus == NodeStatus.ONLINE || currentStatus == NodeStatus.CONNECTING) {
      TraffMonetizerService.stop(getApplication())
      TraffMonetizerEngine.stopNode(getApplication(), "User toggled off")
    } else {
      val currentSettings = repository.getSettings()
      if (currentSettings.token.isBlank()) {
        TraffMonetizerEngine.appendLog(
          LogLevel.ERROR,
          "No Application Token configured! Please paste your token from TraffMonetizer Dashboard in Settings."
        )
      } else {
        TraffMonetizerService.start(getApplication())
      }
    }
  }

  fun saveSettings(newSettings: AppSettings) {
    repository.saveSettings(newSettings)
    TraffMonetizerEngine.appendLog(LogLevel.INFO, "Settings updated. Device alias: ${newSettings.deviceName}")
  }

  fun refreshNetwork() {
    TraffMonetizerEngine.updateNetworkInfo(getApplication())
  }

  fun clearLogs() {
    TraffMonetizerEngine.clearLogs()
  }

  fun addRemoteNode(node: RemoteNodeEntity) {
    viewModelScope.launch {
      repository.addRemoteNode(node)
      TraffMonetizerEngine.appendLog(LogLevel.SUCCESS, "Added remote tracking node '${node.name}' (${node.ip})")
    }
  }

  fun deleteRemoteNode(id: Long) {
    viewModelScope.launch {
      repository.deleteRemoteNode(id)
    }
  }

  fun clearHistory() {
    viewModelScope.launch {
      repository.clearHistory()
      TraffMonetizerEngine.appendLog(LogLevel.INFO, "Traffic session history cleared.")
    }
  }
}
