package com.example.node

import android.content.Context
import android.provider.Settings
import java.util.UUID

/**
 * Device identity used by the TraffMonetizer protocol.
 *
 * The production cli_v2 client (v1.3.3) identifies each node with a 16-byte
 * UUID (uuid crate), persisted across runs in cli_device_ids.json keyed by
 * bind IP. We derive a stable UUID from Settings.Secure.ANDROID_ID instead:
 * same 16 bytes on the wire, stable across restarts and app updates, and no
 * extra state to manage. ANDROID_ID is per-app-signing-key and per-user on
 * API 26+, so this identifies *this* installation on *this* device.
 */
internal object DeviceIdentity {

  /**
   * @return the 16-byte instance id (a UUID in standard MSB-first order), or
   *         null if ANDROID_ID is unavailable. The caller surfaces the null
   *         as a real failure rather than registering a bogus id.
   */
  fun instanceId(context: Context): ByteArray? {
    val androidId = Settings.Secure.getString(context.contentResolver, Settings.Secure.ANDROID_ID)
    if (androidId.isNullOrBlank()) return null
    val uuid = UUID.nameUUIDFromBytes(androidId.toByteArray(Charsets.UTF_8))
    val bytes = ByteArray(16)
    val msb = uuid.mostSignificantBits
    val lsb = uuid.leastSignificantBits
    for (i in 0 until 8) bytes[i] = (msb shr (56 - 8 * i)).toByte()
    for (i in 8 until 16) bytes[i] = (lsb shr (56 - 8 * (i - 8))).toByte()
    return bytes
  }

  /** Short, non-reversible label for logs. Never log the full id or the token. */
  fun shortLabel(instanceId: ByteArray): String =
    instanceId.take(4).joinToString("") { "%02x".format(it) }
}
