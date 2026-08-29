package com.example.node

import android.content.Context
import android.provider.Settings
import java.security.MessageDigest

/**
 * Device identity used by the TraffMonetizer protocol.
 *
 * The reference SDK (`com.tm.C0155p`) derives the instance id as
 * `MD5(Settings.Secure.ANDROID_ID)` — 16 raw bytes — and sends it in the header
 * of every message. ANDROID_ID is per-app-signing-key and per-user on API 26+,
 * so this identifies *this* installation on *this* device and nothing else.
 */
internal object DeviceIdentity {

  /**
   * @return the 16-byte instance id, or null if ANDROID_ID is unavailable.
   *         The reference SDK would crash in that case; we surface it instead so
   *         the caller can report a real failure rather than register a bogus id.
   */
  fun instanceId(context: Context): ByteArray? {
    val androidId = Settings.Secure.getString(context.contentResolver, Settings.Secure.ANDROID_ID)
    if (androidId.isNullOrBlank()) return null
    return MessageDigest.getInstance("MD5").digest(androidId.toByteArray(Charsets.UTF_8))
  }

  /** Short, non-reversible label for logs. Never log the full id or the token. */
  fun shortLabel(instanceId: ByteArray): String =
    instanceId.take(4).joinToString("") { "%02x".format(it) }
}
