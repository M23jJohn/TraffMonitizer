package com.example.node.protocol

import java.io.DataInputStream
import java.io.IOException

/**
 * Reads one frame at a time from the node socket.
 *
 * Mirrors the dispatch in the reference SDK's `com.tm.X1.a`: read the type byte,
 * read the fixed 16-byte instance id, then read the fields for that type. The
 * instance id is echoed back by the server and carries no new information, so it
 * is consumed and discarded.
 */
internal class InboundParser(private val input: DataInputStream) {

  class ProtocolException(message: String) : IOException(message)

  /** Blocks until a full frame is available. Throws on EOF or an unknown type. */
  fun readMessage(): InboundMessage {
    val typeByte = input.read()
    if (typeByte < 0) throw ProtocolException("Server closed the connection")
    val type = MessageType.fromCode(typeByte)
      ?: throw ProtocolException("Unknown message type $typeByte")

    // Header: instance id, always 16 raw bytes.
    Wire.readBytes(input, INSTANCE_ID_BYTES)

    return when (type) {
      MessageType.PING_MESSAGE -> InboundMessage.Ping
      MessageType.PONG_RESPONSE -> InboundMessage.Pong
      MessageType.GET_NAME_RESPONSE -> InboundMessage.GetNameResponse(Wire.readString(input))

      // Status-carrying responses: [bool][string statusMessage][extra...]
      MessageType.HELLO_RESPONSE -> statusPair().let { (ok, msg) ->
        InboundMessage.HelloResponse(ok, msg)
      }

      MessageType.BYE_RESPONSE -> statusPair().let { (ok, msg) ->
        InboundMessage.ByeResponse(ok, msg)
      }

      MessageType.STAT_RESPONSE -> statusPair().let { (ok, msg) ->
        InboundMessage.StatResponse(
          success = ok,
          statusMessage = msg,
          inboundTraffic = Wire.readLongLe(input),
          outboundTraffic = Wire.readLongLe(input),
          requestsCount = Wire.readLongLe(input),
        )
      }

      MessageType.BALANCE_RESPONSE -> statusPair().let { (ok, msg) ->
        InboundMessage.BalanceResponse(
          success = ok,
          statusMessage = msg,
          balance = Double.fromBits(Wire.readLongLe(input)),
          last30Days = Double.fromBits(Wire.readLongLe(input)),
        )
      }

      MessageType.SET_NAME_RESPONSE -> statusPair().let { (ok, msg) ->
        InboundMessage.SetNameResponse(ok, msg)
      }

      MessageType.FETCH_RESPONSE,
      MessageType.ACCEPT_READY_RESPONSE,
      MessageType.ACCEPT_UNREADY_RESPONSE -> statusPair().let { (ok, msg) ->
        InboundMessage.StatusOnly(type, ok, msg)
      }

      // Channel-scoped status replies: [bool][string][int channelId]
      MessageType.PACKET_RESPONSE,
      MessageType.CONNECT_RESPONSE,
      MessageType.EOF_RESPONSE -> statusPair().let { (ok, msg) ->
        InboundMessage.ChannelStatus(type, ok, msg, Wire.readIntLe(input))
      }

      // Channel-scoped commands: [int channelId][type-specific...]
      MessageType.PACKET -> {
        val channelId = Wire.readIntLe(input)
        val length = Wire.readIntLe(input)
        if (length < 0 || length > MAX_PACKET_BYTES) {
          throw ProtocolException("Rejecting PACKET of $length bytes")
        }
        InboundMessage.Packet(channelId, Wire.readBytes(input, length))
      }

      MessageType.CONNECT -> InboundMessage.Connect(
        channelId = Wire.readIntLe(input),
        host = Wire.readString(input),
        port = Wire.readIntLe(input),
      )

      MessageType.CONNECT_V2 -> {
        val channelId = Wire.readIntLe(input)
        val host = Wire.readString(input)
        val port = Wire.readIntLe(input)
        val featureCount = Wire.readIntLe(input)
        if (featureCount < 0 || featureCount > MAX_FEATURES) {
          throw ProtocolException("Rejecting CONNECT_V2 with $featureCount features")
        }
        val features = LinkedHashMap<String, String>(featureCount.coerceAtMost(16))
        repeat(featureCount) {
          features[Wire.readString(input)] = Wire.readString(input)
        }
        InboundMessage.ConnectV2(channelId, host, port, features)
      }

      MessageType.UDP_BIND_MESSAGE -> InboundMessage.UdpBind(
        channelId = Wire.readIntLe(input),
        host = Wire.readString(input),
        port = Wire.readIntLe(input),
      )

      MessageType.EOF -> InboundMessage.Eof(Wire.readIntLe(input))

      MessageType.ACK -> InboundMessage.Ack(
        channelId = Wire.readIntLe(input),
        value = Wire.readLongLe(input),
      )

      // Types the server never sends to a node (they are node -> server only).
      MessageType.NONE,
      MessageType.HELLO,
      MessageType.BYE,
      MessageType.FETCH,
      MessageType.ACCEPT_READY,
      MessageType.ACCEPT_UNREADY,
      MessageType.STAT_MESSAGE,
      MessageType.BALANCE_MESSAGE,
      MessageType.UDP_PACKET,
      MessageType.SET_NAME_MESSAGE,
      MessageType.GET_NAME_MESSAGE -> InboundMessage.Unhandled(type)
    }
  }

  private fun statusPair(): Pair<Boolean, String> {
    val success = input.readBoolean()
    return success to Wire.readString(input)
  }

  companion object {
    const val INSTANCE_ID_BYTES = 16

    /** The reference relays in 8 KiB chunks; allow generous headroom, reject absurd sizes. */
    private const val MAX_PACKET_BYTES = 1 shl 22
    private const val MAX_FEATURES = 64
  }
}
