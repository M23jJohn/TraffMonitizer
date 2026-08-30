package com.example.node.protocol

import java.io.DataInputStream

/**
 * Inbound frames, parsed exactly as the cli_v2 receiver does.
 *
 * Frame layout: `[1 byte type][16 bytes instanceId][type-specific fields]`.
 * There is no overall length prefix; the type determines how much to read.
 * Type codes below are the verified cli_v2 codes (client 1-20, responses
 * 101-115).
 */
internal sealed interface InboundMessage {

  /** PING_MESSAGE (12) — server-initiated keep-alive. Header only. */
  data object Ping : InboundMessage

  /** PONG_RESPONSE (111) — reply to our PING_MESSAGE. Header only. */
  data object Pong : InboundMessage

  /** HELLO_RESPONSE (101) — authentication/registration verdict. */
  data class HelloResponse(val success: Boolean, val statusMessage: String) : InboundMessage

  /** BYE_RESPONSE (102). */
  data class ByeResponse(val success: Boolean, val statusMessage: String) : InboundMessage

  /** STAT_RESPONSE (109) — server-side traffic counters for this node. */
  data class StatResponse(
    val success: Boolean,
    val statusMessage: String,
    val inboundTraffic: Long,
    val outboundTraffic: Long,
    val requestsCount: Long,
  ) : InboundMessage

  /** BALANCE_RESPONSE (110) — account balance, two IEEE-754 doubles. */
  data class BalanceResponse(
    val success: Boolean,
    val statusMessage: String,
    val balance: Double,
    val last30Days: Double,
  ) : InboundMessage

  /** SET_NAME_RESPONSE (112). */
  data class SetNameResponse(val success: Boolean, val statusMessage: String) : InboundMessage

  /** GET_NAME_RESPONSE (113). */
  data class GetNameResponse(val name: String) : InboundMessage

  /** ACCEPT_READY_RESPONSE (105) / ACCEPT_UNREADY_RESPONSE (106) / FETCH_RESPONSE (103). */
  data class StatusOnly(
    val type: MessageType,
    val success: Boolean,
    val statusMessage: String,
  ) : InboundMessage

  /** CONNECT (7) — open a TCP channel to [host]:[port]. */
  data class Connect(val channelId: Int, val host: String, val port: Int) : InboundMessage

  /** CONNECT_V2 (19) — as CONNECT plus a feature map (e.g. "wndi" TCP window hint). */
  data class ConnectV2(
    val channelId: Int,
    val host: String,
    val port: Int,
    val features: Map<String, String>,
  ) : InboundMessage

  /** UDP_BIND_MESSAGE (16). Parsed but not served by this client. */
  data class UdpBind(val channelId: Int, val host: String, val port: Int) : InboundMessage

  /** PACKET (4) — payload to write to the channel's socket. */
  data class Packet(val channelId: Int, val payload: ByteArray) : InboundMessage {
    override fun equals(other: Any?) =
      other is Packet && channelId == other.channelId && payload.contentEquals(other.payload)

    override fun hashCode() = 31 * channelId + payload.contentHashCode()
  }

  /** EOF (8) — remote end of the channel closed. */
  data class Eof(val channelId: Int) : InboundMessage

  /** ACK (20) — flow-control acknowledgement for a channel. */
  data class Ack(val channelId: Int, val value: Long) : InboundMessage

  /** Channel-scoped status replies: PACKET_RESPONSE (104), CONNECT_RESPONSE (107), EOF_RESPONSE (108). */
  data class ChannelStatus(
    val type: MessageType,
    val success: Boolean,
    val statusMessage: String,
    val channelId: Int,
  ) : InboundMessage

  /** A type this client recognises but does not act on. */
  data class Unhandled(val type: MessageType) : InboundMessage
}
