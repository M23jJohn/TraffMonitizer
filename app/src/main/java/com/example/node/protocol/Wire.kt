package com.example.node.protocol

import java.io.DataInputStream
import java.io.OutputStream

/**
 * Wire primitives for the TraffMonetizer protocol, verified against the cli_v2
 * binary (v1.3.3):
 *  - integers are LITTLE-ENDIAN (read_u32 0x6f445 / read_u64 0x6f4ce write
 *    with no bswap; write_u32 0x6d282)
 *  - String = 4-byte LE length + UTF-8 bytes, 0 < len <= 1 MiB
 *    (read_string 0x6f8be: `cmpl $0x100000, %esi; jbe`)
 *  - the 16-byte instance id is a UUID written in MIXED-ENDIAN (Microsoft GUID)
 *    layout: the encoder 0x6c0aa does `rolw $8` on the first four u16s of the
 *    in-memory RFC 4122 bytes and copies the last 8 verbatim — i.e. the first
 *    4 bytes reversed, bytes 4-7 pair-swapped. The decoder 0x41907 is the
 *    exact inverse. This matches uuid::Uuid::to_bytes_le().
 */
internal object Wire {

  fun intLe(value: Int): ByteArray = ByteArray(4) { i -> (value shr (i * 8)).toByte() }

  fun longLe(value: Long): ByteArray = ByteArray(8) { i -> (value shr (i * 8)).toByte() }

  /**
   * Instance id in GUID (mixed-endian) order, as the server expects it.
   *
   * @param id 16 bytes in RFC 4122 (uuid.as_bytes()) order
   */
  fun instanceIdGuid(id: ByteArray): ByteArray {
    require(id.size == 16) { "instance id must be 16 bytes" }
    return byteArrayOf(
      id[3], id[2], id[1], id[0],
      id[5], id[4],
      id[7], id[6],
      id[8], id[9], id[10], id[11],
      id[12], id[13], id[14], id[15],
    )
  }

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
