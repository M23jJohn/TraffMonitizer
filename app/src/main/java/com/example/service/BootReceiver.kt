package com.example.service

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.example.data.repository.TraffRepository

class BootReceiver : BroadcastReceiver {
  constructor() : super()

  override fun onReceive(context: Context, intent: Intent) {
    if (intent.action == Intent.ACTION_BOOT_COMPLETED || intent.action == "android.intent.action.QUICKBOOT_POWERON") {
      val repository = TraffRepository(context)
      val settings = repository.getSettings()
      if (settings.autoStartOnBoot && settings.token.isNotBlank()) {
        TraffMonetizerService.start(context)
      }
    }
  }
}
