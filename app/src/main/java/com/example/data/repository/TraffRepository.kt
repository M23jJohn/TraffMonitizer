package com.example.data.repository

import android.content.Context
import androidx.room.Room
import com.example.data.local.RemoteNodeEntity
import com.example.data.local.TrafficSessionEntity
import com.example.data.local.TraffDatabase
import com.example.data.model.AppSettings
import com.example.data.preferences.PreferenceManager
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow

class TraffRepository(context: Context) {
  private val database = Room.databaseBuilder(
    context.applicationContext,
    TraffDatabase::class.java,
    "traff_monetizer.db"
  ).fallbackToDestructiveMigration().build()

  private val preferenceManager = PreferenceManager(context.applicationContext)

  val settingsFlow: StateFlow<AppSettings> = preferenceManager.settingsFlow
  val allSessions: Flow<List<TrafficSessionEntity>> = database.trafficDao().getAllSessions()
  val totalLifetimeBytes: Flow<Long?> = database.trafficDao().getTotalLifetimeBytes()
  val totalLifetimeEarnings: Flow<Double?> = database.trafficDao().getTotalLifetimeEarnings()
  val remoteNodes: Flow<List<RemoteNodeEntity>> = database.remoteNodeDao().getAllNodes()

  fun getSettings(): AppSettings = preferenceManager.loadSettings()

  fun saveSettings(settings: AppSettings) {
    preferenceManager.saveSettings(settings)
  }

  fun updateToken(token: String) {
    preferenceManager.updateToken(token)
  }

  fun updateDeviceName(name: String) {
    preferenceManager.updateDeviceName(name)
  }

  suspend fun recordSession(startTime: Long, endTime: Long, bytesTransferred: Long, earningsUsd: Double) {
    if (bytesTransferred > 0) {
      database.trafficDao().insertSession(
        TrafficSessionEntity(
          startTime = startTime,
          endTime = endTime,
          bytesTransferred = bytesTransferred,
          earningsUsd = earningsUsd
        )
      )
    }
  }

  suspend fun addRemoteNode(node: RemoteNodeEntity) {
    database.remoteNodeDao().insertOrUpdateNode(node)
  }

  suspend fun deleteRemoteNode(id: Long) {
    database.remoteNodeDao().deleteNode(id)
  }

  suspend fun clearHistory() {
    database.trafficDao().clearSessions()
  }
}
