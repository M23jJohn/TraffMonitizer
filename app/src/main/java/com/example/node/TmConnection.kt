package com.example.node

import com.example.node.protocol.InboundMessage
import com.example.node.protocol.InboundParser
import com.example.node.protocol.MessageType
import com.example.node.protocol.OutboundMessages
import com.example.node.protocol.Wire
import java.io.BufferedOutputStream
import java.io.DataInputStream
import java.net.InetSocketAddress
import java.util.concurrent.ConcurrentHashMap
import java.util.concurrent.atomic.AtomicBoolean
import javax.net.ssl.SSLSocketFactory
import kotlinx.coroutines.CompletableDeferred
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import kotlinx.coroutines.selects.select
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import kotlinx.coroutines.withTimeout
import kotlinx.coroutines.withTimeoutOrNull
import kotlinx.coroutines.yield

/**
 * TLS connection to a TraffMonetizer edge server.
 *
 * Transport facts reproduced from the reference SDK:
 *  - `SSLSocketFactory.getDefault().createSocket()` (`com.tm.C0075d3`) with
 *    tcpNoDelay=true and 1 MiB send/receive buffers; 10 s connect timeout.
 *  - HELLO carries token + deviceType 3 + "<version>" (`com.tm.C5`, `com.tm.O0`).
 *  - PING_MESSAGE every 20 s (`com.tm.V`); a missing PONG_RESPONSE within the
 *    window breaks the connection (`com.tm.Q4`/`X0` "OutPing: timeout!").
 *  - On a broken socket the connection bloc retries after 10 s (`com.tm.W`).
 *  - The server drives the proxy: CONNECT/CONNECT_V2 opens a TCP channel, the
 *    session relays PACKET payloads between that socket and the server.
 *
 * All TLS settings are the platform defaults — nothing here disables or overrides
 * certificate validation.
 */
internal class TmConnection(
  private val config: NodeConfig,
  private val scope: CoroutineScope,
  private val onStatus: (NodeConnectionStatus) -> Unit,
  private val onLog: (level: LogLevel, message: String, tag: String) -> Unit,
  private val trafficCounter: TrafficCounter,
) {
  internal enum class LogLevel { INFO, DATA, SUCCESS, WARN, ERROR }

  private val outgoing = Channel<ByteArray>(capacity = 128)
  private val running = AtomicBoolean(false)

  /** Pending command/response pairs keyed by message type. */
  private val pending = ConcurrentHashMap<MessageType, CompletableDeferred<InboundMessage>>()
  private val pendingLock = Mutex()

  /** Active proxy channels by id. */
  private val channels = ConcurrentHashMap<Int, ProxyChannel>()

  private var socket: javax.net.ssl.SSLSocket? = null
  private var outputStream: BufferedOutputStream? = null

  /**
   * Performs resolve -> connect -> HELLO and then serves until the socket dies.
   * Returns only after the connection is over (cleanly or broken).
   */
  suspend fun connectAndServe(): Result<Unit> {
    // --- Resolve ---
    val serverHost = if (config.serverHost.isNotBlank()) {
      config.serverHost
    } else {
      try {
        LoadBalancerResolver.resolve(config.balancerHost)
      } catch (e: LoadBalancerResolver.ResolveException) {
        return Result.failure(e)
      }
    }

    // --- TLS connect ---
    onStatus(NodeConnectionStatus.SocketConnecting)
    val sock = SSLSocketFactory.getDefault().createSocket() as javax.net.ssl.SSLSocket
    sock.tcpNoDelay = true
    sock.sendBufferSize = 1_048_576
    sock.receiveBufferSize = 1_048_576
    try {
      withTimeout(SOCKET_CONNECT_TIMEOUT_MS) {
        sock.connect(InetSocketAddress(serverHost, config.apiPort))
      }
    } catch (e: Exception) {
      runCatching { sock.close() }
      return Result.failure(ConnectionException("TCP/TLS connect to $serverHost:${config.apiPort} failed", e))
    }
    socket = sock
    outputStream = BufferedOutputStream(sock.getOutputStream())
    running.set(true)
    onLog(LogLevel.INFO, "TLS connection established to $serverHost:${config.apiPort}", "SOCKET")

    // --- HELLO ---
    onStatus(NodeConnectionStatus.Authenticating)
    val helloResult = sendHello()
    val hello = helloResult.getOrNull()
    if (hello == null) {
      closeQuietly()
      return Result.failure(
        helloResult.exceptionOrNull()
          ?: ConnectionException("HELLO did not complete")
      )
    }

    if (hello.success) {
      onStatus(NodeConnectionStatus.Connected(serverHost, hello.statusMessage))
      onLog(
        LogLevel.SUCCESS,
        "Hello accepted: registered with TraffMonetizer (${hello.statusMessage.ifBlank { "OK" }})",
        "HELLO"
      )
    } else {
      closeQuietly()
      return Result.failure(
        ConnectionException("Handshake rejected: ${hello.statusMessage.ifBlank { "authentication failed" }}")
      )
    }

    // --- Read loop + ping + proxy ---
    val reader = scope.launch { readLoop() }
    val writer = scope.launch { writeLoop() }
    val pinger = scope.launch { pingLoop() }

    reader.join()
    running.set(false)
    writer.cancel()
    pinger.cancel()
    closeAllChannels()
    closeQuietly()

    return Result.success(Unit)
  }

  // ---- Message sending ----

  private suspend fun sendHello(): Result<InboundMessage.HelloResponse> {
    val frame = OutboundMessages.hello(
      instanceId = config.instanceId,
      token = config.token,
      deviceType = NodeConfig.DEVICE_TYPE_ANDROID,
      version = config.helloVersion,
    )
    sendFrame(frame)
    val response = awaitResponse(MessageType.HELLO_RESPONSE, HELLO_TIMEOUT_MS)
    return when (response) {
      is InboundMessage.HelloResponse -> Result.success(response)
      null -> Result.failure(ConnectionException("HELLO_RESPONSE did not arrive within ${HELLO_TIMEOUT_MS / 1000}s"))
      else -> Result.failure(ConnectionException("Unexpected reply to HELLO: ${response::class.simpleName}"))
    }
  }

  /**
   * Sends a channel message and awaits its [expectedResponse] type. Returns null
   * on timeout — the caller decides how to report it.
   */
  suspend fun request(
    frame: ByteArray,
    expectedResponse: MessageType,
    timeoutMs: Long,
  ): InboundMessage? {
    if (!running.get()) return null
    val deferred = CompletableDeferred<InboundMessage>()
    pending[expectedResponse] = deferred
    try {
      sendFrame(frame)
      return withTimeoutOrNull(timeoutMs) { deferred.await() }
    } finally {
      pending.remove(expectedResponse)
    }
  }

  private suspend fun sendFrame(frame: ByteArray) {
    try {
      outgoing.send(frame)
    } catch (e: Exception) {
      throw ConnectionException("Send queue closed", e)
    }
  }

  private fun writeLoop() {
    val stream = outputStream ?: return
    while (scope.isActive && running.get()) {
      val frame = runCatching { outgoing.tryReceive().getOrNull() }.getOrNull() ?: break
      runCatching {
        stream.write(frame)
        stream.flush()
        trafficCounter.addOutbound(frame.size.toLong())
      }
    }
  }

  // Wait for a frame to arrive without blocking the caller thread for the full queue drain
  private suspend fun awaitResponse(type: MessageType, timeoutMs: Long): InboundMessage? {
    val deferred = CompletableDeferred<InboundMessage>()
    pending[type] = deferred
    try {
      return withTimeoutOrNull(timeoutMs) { deferred.await() }
    } finally {
      pending.remove(type)
    }
  }

  private suspend fun pingLoop() {
    while (scope.isActive && running.get()) {
      delay(PING_INTERVAL_MS)
      if (!running.get()) return
      runCatching { sendFrame(OutboundMessages.ping(config.instanceId)) }
      // PONG arrives via readLoop; its absence is covered by the server dropping us.
    }
  }

  // ---- Inbound handling ----

  private suspend fun readLoop() {
    val stream = socket?.getInputStream() ?: return
    val parser = InboundParser(DataInputStream(stream))
    try {
      while (scope.isActive && running.get()) {
        val message = parser.readMessage()
        handle(message)
        yield()
      }
    } catch (e: Exception) {
      onLog(LogLevel.WARN, "Connection lost: ${e.message ?: e.javaClass.simpleName}", "SOCKET")
    }
  }

  private suspend fun handle(message: InboundMessage) {
    when (message) {
      is InboundMessage.Ping -> {
        // The reference's receiver answers server pings with a PING back; the
        // session stays alive via our own pinger.
        runCatching { sendFrame(OutboundMessages.ping(config.instanceId)) }
      }

      is InboundMessage.HelloResponse,
      is InboundMessage.StatResponse,
      is InboundMessage.BalanceResponse,
      is InboundMessage.SetNameResponse,
      is InboundMessage.ByeResponse -> {
        // These are awaited via pending; complete them.
        val type = MessageType.fromCode(codeFor(message))!!
        pending[type]?.complete(message)
      }

      is InboundMessage.Pong -> Unit

      is InboundMessage.Connect -> openChannel(message.channelId, message.host, message.port, emptyMap())
      is InboundMessage.ConnectV2 -> openChannel(message.channelId, message.host, message.port, message.features)

      is InboundMessage.Packet -> channels[message.channelId]?.write(message.payload)

      is InboundMessage.Eof -> channels[message.channelId]?.closeFromServer()

      is InboundMessage.Ack -> channels[message.channelId]?.onAck(message.value)

      is InboundMessage.ChannelStatus,
      is InboundMessage.StatusOnly,
      is InboundMessage.GetNameResponse,
      is InboundMessage.UdpBind,
      is InboundMessage.Unhandled -> Unit
    }
  }

  private fun codeFor(message: InboundMessage): Int = when (message) {
    is InboundMessage.HelloResponse -> MessageType.HELLO_RESPONSE.code
    is InboundMessage.StatResponse -> MessageType.STAT_RESPONSE.code
    is InboundMessage.BalanceResponse -> MessageType.BALANCE_RESPONSE.code
    is InboundMessage.SetNameResponse -> MessageType.SET_NAME_RESPONSE.code
    is InboundMessage.ByeResponse -> MessageType.BYE_RESPONSE.code
    else -> MessageType.NONE.code
  }

  private suspend fun openChannel(id: Int, host: String, port: Int, features: Map<String, String>) {
    if (!running.get()) return
    onLog(LogLevel.DATA, "Channel [$id]: serving request for $host:$port", "RELAY")
    val channel = ProxyChannel(
      id = id,
      host = host,
      port = port,
      connection = this,
      onFinished = { channels.remove(id) },
    )
    val existing = channels.putIfAbsent(id, channel)
    if (existing != null) {
      existing.closeFromServer()
      channels[id] = channel
    }
    channel.start()
  }

  internal suspend fun sendChannelFrame(frame: ByteArray) {
    if (running.get()) sendFrame(frame)
  }

  internal fun addChannelBytes(bytes: Int, inbound: Boolean) {
    if (inbound) trafficCounter.addInbound(bytes.toLong())
    else trafficCounter.addOutbound(bytes.toLong())
  }

  internal fun scopeActive(): Boolean = scope.isActive && running.get()

  internal val configForChannels: NodeConfig get() = config
  internal val ioScope: CoroutineScope get() = scope

  private suspend fun closeAllChannels() {
    channels.values.forEach { it.closeFromServer() }
    channels.clear()
  }

  private fun closeQuietly() {
    running.set(false)
    runCatching { outgoing.close() }
    runCatching { socket?.close() }
    socket = null
    outputStream = null
  }

  /** Clean shutdown: BYE then close. Call once; [connectAndServe] returns afterward. */
  suspend fun shutdown() {
    if (running.get()) {
      runCatching { sendFrame(OutboundMessages.bye(config.instanceId)) }
      delay(100)
    }
    closeQuietly()
  }

  class ConnectionException(message: String, cause: Throwable? = null) : Exception(message, cause)

  companion object {
    const val SOCKET_CONNECT_TIMEOUT_MS = 10_000L
    val HELLO_TIMEOUT_MS = 10_000L
    val PING_INTERVAL_MS = 20_000L
  }
}
