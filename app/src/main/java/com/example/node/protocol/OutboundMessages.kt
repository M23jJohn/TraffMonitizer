package com.example.node.protocol

/**
 * Outbound frame construction.
 *
 * Verified against the cli_v2 binary (v1.3.3):
 *
 *     [1 byte: message code][16 bytes: instance id in GUID order][payload...]
 *
 * The writer tail (0x423b5) emits the code byte, then the instance id via the
 * GUID transform (0x6c0aa — first 4 bytes reversed, 4-7 pair-swapped), then
 * jumps to the per-code field plan to serialise the payload. There is no
 * overall length prefix; the receiver knows each type's field layout.
 */
internal object OutboundMessages {

  private fun header(type: MessageType, instanceId: ByteArray): ByteArray =
    byteArrayOf(type.code.toByte()) + Wire.instanceIdGuid(instanceId)

  /**
   * HELLO (code 1) — authentication and node registration.
   * Field plan 1 (poll 0x71053): [String token][u32 deviceType][String version].
   */
  fun hello(instanceId: ByteArray, token: String, deviceType: Int, version: String): ByteArray =
    header(MessageType.HELLO, instanceId) +
      Wire.string(token) +
      Wire.intLe(deviceType) +
      Wire.string(version)

  /** BYE (2) — clean shutdown notice. Header only. */
  fun bye(instanceId: ByteArray): ByteArray = header(MessageType.BYE, instanceId)

  /** PING_MESSAGE (12) — keep-alive; answered by PONG_RESPONSE (111). Header only. */
  fun ping(instanceId: ByteArray): ByteArray = header(MessageType.PING_MESSAGE, instanceId)

  /** STAT_MESSAGE (9) — request the server's traffic counters for this node. Header only. */
  fun statRequest(instanceId: ByteArray): ByteArray = header(MessageType.STAT_MESSAGE, instanceId)

  /** BALANCE_MESSAGE (10) — request the account balance. Header only. */
  fun balanceRequest(instanceId: ByteArray): ByteArray =
    header(MessageType.BALANCE_MESSAGE, instanceId)

  /** GET_NAME_MESSAGE (15) — read the device alias the backend has on record. */
  fun getName(instanceId: ByteArray): ByteArray = header(MessageType.GET_NAME_MESSAGE, instanceId)

  /** SET_NAME_MESSAGE (14) — set the device alias shown in the dashboard. */
  fun setName(instanceId: ByteArray, name: String): ByteArray =
    header(MessageType.SET_NAME_MESSAGE, instanceId) + Wire.string(name)

  // --- Proxy channel messages (node -> server) ---

  /**
   * CONNECT_RESPONSE (107) — result of opening a channel.
   * Layout: header + statusByte + string(statusMessage) + int(channelId)
   *         + int(remoteIp.length) + remoteIp + int(localPort)
   */
  fun connectResponse(
    instanceId: ByteArray,
    success: Boolean,
    statusMessage: String,
    channelId: Int,
    remoteIp: ByteArray,
    localPort: Int,
  ): ByteArray =
    header(MessageType.CONNECT_RESPONSE, instanceId) +
      byteArrayOf(if (success) 1 else 0) +
      Wire.string(statusMessage) +
      Wire.intLe(channelId) +
      Wire.intLe(remoteIp.size) + remoteIp +
      Wire.intLe(localPort)

  /**
   * PACKET (4) — payload read from a channel's socket.
   * Layout: header + int(channelId) + int(payload.length) + payload
   */
  fun packet(instanceId: ByteArray, channelId: Int, payload: ByteArray, length: Int): ByteArray =
    header(MessageType.PACKET, instanceId) +
      Wire.intLe(channelId) +
      Wire.intLe(length) +
      (if (length == payload.size) payload else payload.copyOf(length))

  /** EOF (8) — a channel's socket reached end-of-stream. */
  fun eof(instanceId: ByteArray, channelId: Int): ByteArray =
    header(MessageType.EOF, instanceId) + Wire.intLe(channelId)

  /** ACCEPT_READY (5) — node is ready to serve. Header only. */
  fun acceptReady(instanceId: ByteArray): ByteArray =
    header(MessageType.ACCEPT_READY, instanceId)

  /** ACCEPT_UNREADY (6) — node is not accepting. Header only. */
  fun acceptUnready(instanceId: ByteArray): ByteArray =
    header(MessageType.ACCEPT_UNREADY, instanceId)
}
