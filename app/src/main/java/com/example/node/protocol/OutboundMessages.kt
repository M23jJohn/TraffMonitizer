package com.example.node.protocol

/**
 * Outbound frame construction.
 *
 * Every outbound message in the reference SDK derives from `com.tm.Z0`
 * (`...repository.api.command.Message`) whose `b()` produces:
 *
 *     [1 byte: MessageType ordinal][instanceId bytes, raw and un-prefixed][payload...]
 *
 * There is no overall length prefix — the receiver knows each type's field
 * layout and reads exactly that many bytes. `instanceId` is always the raw
 * 16-byte MD5 digest, written verbatim (see `com.tm.Z0.a()` / `com.tm.O0.a()`).
 */
internal object OutboundMessages {

  private fun header(type: MessageType, instanceId: ByteArray): ByteArray =
    byteArrayOf(type.code.toByte()) + instanceId

  /**
   * HELLO — authentication and node registration (reference: `com.tm.O0`).
   * Payload: string(token) + int(deviceType) + string(version)
   */
  fun hello(instanceId: ByteArray, token: String, deviceType: Int, version: String): ByteArray =
    header(MessageType.HELLO, instanceId) +
      Wire.string(token) +
      Wire.intLe(deviceType) +
      Wire.string(version)

  /** BYE — clean shutdown notice. Header only. */
  fun bye(instanceId: ByteArray): ByteArray = header(MessageType.BYE, instanceId)

  /** PING_MESSAGE — keep-alive; answered by PONG_RESPONSE. Header only. */
  fun ping(instanceId: ByteArray): ByteArray = header(MessageType.PING_MESSAGE, instanceId)

  /** STAT_MESSAGE — request the server's traffic counters for this node. Header only. */
  fun statRequest(instanceId: ByteArray): ByteArray = header(MessageType.STAT_MESSAGE, instanceId)

  /** BALANCE_MESSAGE — request the account balance. Header only. */
  fun balanceRequest(instanceId: ByteArray): ByteArray =
    header(MessageType.BALANCE_MESSAGE, instanceId)

  /** GET_NAME_MESSAGE — read the device alias the backend has on record. */
  fun getName(instanceId: ByteArray): ByteArray = header(MessageType.GET_NAME_MESSAGE, instanceId)

  /** SET_NAME_MESSAGE — set the device alias shown in the dashboard. */
  fun setName(instanceId: ByteArray, name: String): ByteArray =
    header(MessageType.SET_NAME_MESSAGE, instanceId) + Wire.string(name)

  // --- Proxy channel messages (node -> server) ---

  /**
   * CONNECT_RESPONSE (reference: `com.tm.C0175s`) — result of opening a channel.
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
   * PACKET (reference: `com.tm.C0059b1`) — payload read from a channel's socket.
   * Layout: header + int(channelId) + int(payload.length) + payload
   */
  fun packet(instanceId: ByteArray, channelId: Int, payload: ByteArray, length: Int): ByteArray =
    header(MessageType.PACKET, instanceId) +
      Wire.intLe(channelId) +
      Wire.intLe(length) +
      (if (length == payload.size) payload else payload.copyOf(length))

  /** EOF (reference: `com.tm.H0`) — a channel's socket reached end-of-stream. */
  fun eof(instanceId: ByteArray, channelId: Int): ByteArray =
    header(MessageType.EOF, instanceId) + Wire.intLe(channelId)

  /** ACCEPT_READY (reference: `com.tm.C0050a`) — node is ready to serve. Header only. */
  fun acceptReady(instanceId: ByteArray): ByteArray =
    header(MessageType.ACCEPT_READY, instanceId)

  /** ACCEPT_UNREADY (reference: `com.tm.C0071d`) — node is not accepting. Header only. */
  fun acceptUnready(instanceId: ByteArray): ByteArray =
    header(MessageType.ACCEPT_UNREADY, instanceId)
}
