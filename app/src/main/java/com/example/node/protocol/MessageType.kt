package com.example.node.protocol

/**
 * Message type codes on the TraffMonetizer wire.
 *
 * Extracted from the production cli_v2 client (v1.3.3): client -> server
 * messages occupy codes 0..20, server -> client responses occupy 101..115.
 * Verified against the binary's serializer dispatch tables (0x1ad31c /
 * 0x1ad370) and its Debug name table — every name below matches the binary's
 * string blob byte for byte.
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
  LOG_MESSAGE(13),
  SET_NAME_MESSAGE(14),
  GET_NAME_MESSAGE(15),
  UDP_BIND_MESSAGE(16),
  ACCEPT_CONTENT_DELIVERY_MESSAGE(17),
  CONTENT_DELIVERY_STAT_MESSAGE(18),
  CONNECT_V2(19),
  ACK(20),

  // Server -> client responses.
  HELLO_RESPONSE(101),
  BYE_RESPONSE(102),
  FETCH_RESPONSE(103),
  PACKET_RESPONSE(104),
  ACCEPT_READY_RESPONSE(105),
  ACCEPT_UNREADY_RESPONSE(106),
  CONNECT_RESPONSE(107),
  EOF_RESPONSE(108),
  STAT_RESPONSE(109),
  BALANCE_RESPONSE(110),
  PONG_RESPONSE(111),
  SET_NAME_RESPONSE(112),
  GET_NAME_RESPONSE(113),
  ACCEPT_CONTENT_DELIVERY_RESPONSE(114),
  CONTENT_DELIVERY_STAT_RESPONSE(115);

  companion object {
    private val byCode = entries.associateBy { it.code }
    fun fromCode(code: Int): MessageType? = byCode[code]
  }
}
