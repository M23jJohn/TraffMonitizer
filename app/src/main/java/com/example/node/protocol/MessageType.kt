package com.example.node.protocol

/**
 * TraffMonetizer wire message types.
 *
 * Reproduced from the reference SDK's `com.tm.EnumC0052a1`
 * (originally `com.traffmonetizer.sdk.repository.api.command.MessageType`).
 * The byte written on the wire is the enum's ordinal, so the declaration order
 * below is significant and must match the reference exactly — including the
 * entries this client never sends.
 */
internal enum class MessageType(val code: Int) {
  NONE(0),
  HELLO(1),
  BYE(2),
  FETCH(3),
  PACKET(4),
  ACCEPT_READY(5),
  ACCEPT_UNREADY(6),
  CONNECT(7),
  EOF(8),
  STAT_MESSAGE(9),
  BALANCE_MESSAGE(10),
  UDP_PACKET(11),
  PING_MESSAGE(12),
  SET_NAME_MESSAGE(13),
  GET_NAME_MESSAGE(14),
  UDP_BIND_MESSAGE(15),
  CONNECT_V2(16),
  ACK(17),
  HELLO_RESPONSE(18),
  BYE_RESPONSE(19),
  FETCH_RESPONSE(20),
  PACKET_RESPONSE(21),
  ACCEPT_READY_RESPONSE(22),
  ACCEPT_UNREADY_RESPONSE(23),
  CONNECT_RESPONSE(24),
  EOF_RESPONSE(25),
  STAT_RESPONSE(26),
  BALANCE_RESPONSE(27),
  PONG_RESPONSE(28),
  SET_NAME_RESPONSE(29),
  GET_NAME_RESPONSE(30);

  companion object {
    private val byCode = entries.associateBy { it.code }

    fun fromCode(code: Int): MessageType? = byCode[code]
  }
}
