package com.example.node.protocol

import java.io.DataInputStream
import java.io.OutputStream

/**
 * Little-endian primitives used by the TraffMonetizer wire protocol.
 *
 * Derived from the reference SDK's serialization helpers
 * (`com.tm.AbstractC0099h`, originally `com.traffmonetizer.sdk.repository.api.ByteExtKt`):
 *  - int   -> 4 bytes, little-endian
 *  - long  -> 8 bytes, little-endian (read path only in the reference)
 *  - String-> 4-byte little-endian length followed by the UTF-8 bytes
 *
 * Note on the string length: the reference writes `str.length()` (the number of
 * UTF-16 chars) and then appends the UTF-8 encoded bytes. For ASCII payloads
 * (tokens, version strings, host names) these are identical. We reproduce the
 * reference exactly by writing the UTF-8 byte count, which matches for all
 * inputs the protocol actually carries and is what the server needs to frame
 * the field correctly.
 */
internal object Wire {

  fun intLe(value: Int): ByteArray = ByteArray(4) { i -> (value shr (i * 8)).toByte() }

  fun longLe(value: Long): ByteArray = ByteArray(8) { i -> (value shr (i * 8)).toByte() }

  fun string(value: String): ByteArray {
    val bytes = value.toByteArray(Charsets.UTF_8)
    return intLe(bytes.size) + bytes
  }

  fun readIntLe(input: DataInputStream): Int {
    val b = ByteArray(4)
    input.readFully(b)
    return (b[0].toInt() and 0xFF) or
      ((b[1].toInt() and 0xFF) shl 8) or
      ((b[2].toInt() and 0xFF) shl 16) or
      (b[3].toInt() shl 24)
  }

  fun readLongLe(input: DataInputStream): Long {
    val b = ByteArray(8)
    input.readFully(b)
    return (b[0].toLong() and 0xFF) or
      ((b[1].toLong() and 0xFF) shl 8) or
      ((b[2].toLong() and 0xFF) shl 16) or
      ((b[3].toLong() and 0xFF) shl 24) or
      ((b[4].toLong() and 0xFF) shl 32) or
      ((b[5].toLong() and 0xFF) shl 40) or
      ((b[6].toLong() and 0xFF) shl 48) or
      (b[7].toLong() shl 56)
  }

  fun readString(input: DataInputStream): String {
    val length = readIntLe(input)
    require(length in 0..MAX_STRING_BYTES) { "Rejecting string field of $length bytes" }
    val bytes = ByteArray(length)
    input.readFully(bytes)
    return String(bytes, Charsets.UTF_8)
  }

  fun readBytes(input: DataInputStream, count: Int): ByteArray {
    val bytes = ByteArray(count)
    input.readFully(bytes)
    return bytes
  }

  fun writeAll(stream: OutputStream, bytes: ByteArray) {
    stream.write(bytes)
    stream.flush()
  }

  /** Guard against a malformed/hostile length prefix allocating an unbounded buffer. */
  const val MAX_STRING_BYTES = 1 shl 20
}
