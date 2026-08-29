package com.example.service

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.app.Service
import android.content.Context
import android.content.Intent
import android.content.pm.ServiceInfo
import android.os.Build
import android.os.IBinder
import androidx.core.app.NotificationCompat
import com.example.MainActivity
import com.example.R
import com.example.data.model.NodeStatus
import com.example.data.repository.TraffRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch

class TraffMonetizerService : Service() {
  private val serviceScope = CoroutineScope(SupervisorJob() + Dispatchers.Main)
  private var updateJob: Job? = null
  private lateinit var notificationManager: NotificationManager

  override fun onCreate() {
    super.onCreate()
    notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
    createNotificationChannel()
  }

  override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
    if (intent?.action == ACTION_STOP) {
      TraffMonetizerEngine.stopNode(this, "Stopped from notification")
      stopForeground(STOP_FOREGROUND_REMOVE)
      stopSelf()
      return START_NOT_STICKY
    }

    val repository = TraffRepository(this)
    val settings = repository.getSettings()

    // Start Foreground immediately
    val notification = buildNotification(
      status = "Starting node...",
      details = "Initializing TraffMonetizer CLI v2..."
    )

    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
      startForeground(
        NOTIFICATION_ID,
        notification,
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.UPSIDE_DOWN_CAKE) {
          ServiceInfo.FOREGROUND_SERVICE_TYPE_DATA_SYNC
        } else {
          0
        }
      )
    } else {
      startForeground(NOTIFICATION_ID, notification)
    }

    TraffMonetizerEngine.startNode(
      context = this,
      token = settings.token,
      deviceName = settings.deviceName,
      settings = settings
    )

    startNotificationUpdates()
    return START_STICKY
  }

  private fun startNotificationUpdates() {
    updateJob?.cancel()
    updateJob = serviceScope.launch {
      while (isActive) {
        delay(1500)
        val status = TraffMonetizerEngine.status.value
        val stats = TraffMonetizerEngine.stats.value

        if (status == NodeStatus.STOPPED) {
          stopForeground(STOP_FOREGROUND_REMOVE)
          stopSelf()
          break
        }

        val title = when (status) {
          NodeStatus.ONLINE -> "TraffMonetizer Node • ONLINE"
          NodeStatus.CONNECTING -> "TraffMonetizer Node • CONNECTING..."
          NodeStatus.PAUSED_BATTERY -> "TraffMonetizer Node • PAUSED (Battery)"
          NodeStatus.PAUSED_WIFI -> "TraffMonetizer Node • PAUSED (Mobile Data)"
          NodeStatus.ERROR -> "TraffMonetizer Node • ERROR"
          NodeStatus.STOPPED -> "TraffMonetizer Node • STOPPED"
        }

        val upStr = TraffMonetizerEngine.formatSpeed(stats.currentUploadBps)
        val downStr = TraffMonetizerEngine.formatSpeed(stats.currentDownloadBps)
        val sharedStr = TraffMonetizerEngine.formatBytes(stats.sessionTotalBytes)
        val details = "⬆ $upStr  ⬇ $downStr • Session: $sharedStr"

        val updatedNotification = buildNotification(title, details)
        notificationManager.notify(NOTIFICATION_ID, updatedNotification)
      }
    }
  }

  private fun buildNotification(status: String, details: String): Notification {
    val openIntent = Intent(this, MainActivity::class.java).apply {
      flags = Intent.FLAG_ACTIVITY_SINGLE_TOP or Intent.FLAG_ACTIVITY_CLEAR_TOP
    }
    val openPendingIntent = PendingIntent.getActivity(
      this,
      0,
      openIntent,
      PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
    )

    val stopIntent = Intent(this, TraffMonetizerService::class.java).apply {
      action = ACTION_STOP
    }
    val stopPendingIntent = PendingIntent.getService(
      this,
      1,
      stopIntent,
      PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
    )

    return NotificationCompat.Builder(this, CHANNEL_ID)
      .setSmallIcon(R.drawable.ic_tm_icon)
      .setContentTitle(status)
      .setContentText(details)
      .setStyle(NotificationCompat.BigTextStyle().bigText(details))
      .setContentIntent(openPendingIntent)
      .setOngoing(true)
      .setPriority(NotificationCompat.PRIORITY_LOW)
      .addAction(android.R.drawable.ic_menu_close_clear_cancel, "Stop Node", stopPendingIntent)
      .build()
  }

  private fun createNotificationChannel() {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
      val channel = NotificationChannel(
        CHANNEL_ID,
        "TraffMonetizer Node Service",
        NotificationManager.IMPORTANCE_LOW
      ).apply {
        description = "Displays live TraffMonetizer node status, bandwidth, and earnings"
        setShowBadge(false)
      }
      notificationManager.createNotificationChannel(channel)
    }
  }

  override fun onDestroy() {
    updateJob?.cancel()
    serviceScope.launch {
      TraffMonetizerEngine.stopNode(this@TraffMonetizerService, "Service destroyed")
    }
    super.onDestroy()
  }

  override fun onBind(intent: Intent?): IBinder? = null

  companion object {
    const val CHANNEL_ID = "traff_monetizer_node_channel"
    const val NOTIFICATION_ID = 4201
    const val ACTION_STOP = "com.example.service.ACTION_STOP"

    fun start(context: Context) {
      val intent = Intent(context, TraffMonetizerService::class.java)
      if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
        context.startForegroundService(intent)
      } else {
        context.startService(intent)
      }
    }

    fun stop(context: Context) {
      val intent = Intent(context, TraffMonetizerService::class.java).apply {
        action = ACTION_STOP
      }
      context.startService(intent)
    }
  }
}
