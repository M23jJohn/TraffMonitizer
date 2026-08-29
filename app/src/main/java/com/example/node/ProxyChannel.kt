package com.example.node

import com.example.node.protocol.OutboundMessages
import java.io.BufferedInputStream
import java.io.BufferedOutputStream
import java.net.InetSocketAddress
import java.net.Socket
import kotlinx.coroutines.launch

/**
 * One proxied TCP session, exactly the shape of the reference's proxy session
 * (`com.tm.D3` + relay loops `com.tm.C0207w3` / `com.tm.C0214x3`):
 *  - server sends CONNECT/CONNECT_V2(id, host, port)
 *  - node opens a plain TCP socket to host:port (non-TLS, 64 KiB buffers —
 *    the reference's `com.tm.C0159p3` uses `new C0075d3(false)`)
 *  - server PACKET(id, bytes) -> write bytes to the socket
 *  - socket reads -> PACKET(id, bytes) back to the server
 *  - EOF(id) either way closes the channel
 */
internal class ProxyChannel(
  private val id: Int,
  private val host: String,
  private val port: Int,
  private val connection: TmConnection,
  private val onFinished: () -> Unit,
) {
  private var socket: Socket? = null
  private var remoteIp: ByteArray = ByteArray(0)
  private var outboundStream: BufferedOutputStream? = null

  fun start() {
    val conn = connection
    // Socket open happens on the connection's scope (IO-backed).
    conn.ioScope.launch {
      try {
        val sock = Socket()
        sock.tcpNoDelay = true
        sock.sendBufferSize = 65_536
        sock.receiveBufferSize = 65_536
        sock.connect(InetSocketAddress(host, port), 15_000)
        socket = sock
        outboundStream = BufferedOutputStream(sock.getOutputStream())
        remoteIp = sock.inetAddress?.address ?: ByteArray(0)

        // CONNECT_RESPONSE: ok + channel id + remote ip + local port
        conn.sendChannelFrame(
          OutboundMessages.connectResponse(
            instanceId = conn.configForChannels.instanceId,
            success = true,
            statusMessage = "connected",
            channelId = id,
            remoteIp = remoteIp,
            localPort = sock.localPort,
          )
        )

        // Relay socket -> server until EOF.
        val input = BufferedInputStream(sock.getInputStream())
        val buffer = ByteArray(RELAY_BUFFER_BYTES)
        while (conn.scopeActive() && !sock.isClosed) {
          val read = try {
            input.read(buffer)
          } catch (e: Exception) {
            break
          }
          if (read < 0) break
          if (read > 0) {
            conn.addChannelBytes(read, inbound = true)
            conn.sendChannelFrame(
              OutboundMessages.packet(conn.configForChannels.instanceId, id, buffer, read)
            )
          }
        }
        conn.sendChannelFrame(OutboundMessages.eof(conn.configForChannels.instanceId, id))
        closeFromServer()
      } catch (e: Exception) {
        conn.sendChannelFrame(
          OutboundMessages.connectResponse(
            instanceId = conn.configForChannels.instanceId,
            success = false,
            statusMessage = e.message ?: e.javaClass.simpleName,
            channelId = id,
            remoteIp = remoteIp,
            localPort = 0,
          )
        )
        closeFromServer()
      }
    }
  }

  /** Server -> socket direction (PACKET frames). */
  suspend fun write(payload: ByteArray) {
    val stream = outboundStream ?: return
    runCatching {
      stream.write(payload)
      stream.flush()
      connection.addChannelBytes(payload.size, inbound = false)
    }
  }

  /** Reference TCP-window handling ("wndi") sets a byte watermark; we only observe. */
  fun onAck(value: Long) = Unit

  /** Marks the channel done; sends nothing more (EOF was already sent when we initiated). */
  fun closeFromServer() {
    runCatching { socket?.close() }
    onFinished()
  }

  companion object {
    const val RELAY_BUFFER_BYTES = 8_192
  }
}
