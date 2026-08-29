package com.example.node

import java.util.concurrent.atomic.AtomicLong

/**
 * Byte counters for traffic this node actually relayed.
 *
 * Incremented only from the transport layer, at the point bytes are read from or
 * written to a socket. [sampleThroughput] converts the deltas since the previous
 * call into bytes/sec so the UI can show a real rate.
 */
internal class TrafficCounter {

  private val inbound = AtomicLong(0)
  private val outbound = AtomicLong(0)

  private var lastSampleAt = 0L
  private var lastInbound = 0L
  private var lastOutbound = 0L

  fun addInbound(bytes: Long) {
    if (bytes > 0) inbound.addAndGet(bytes)
  }

  fun addOutbound(bytes: Long) {
    if (bytes > 0) outbound.addAndGet(bytes)
  }

  fun inboundBytes(): Long = inbound.get()

  fun outboundBytes(): Long = outbound.get()

  fun reset() {
    inbound.set(0)
    outbound.set(0)
    lastSampleAt = 0L
    lastInbound = 0L
    lastOutbound = 0L
  }

  data class Throughput(val downloadBps: Long, val outboundBps: Long)

  /**
   * Rate since the previous call. The first call after [reset] establishes the
   * baseline and reports zero rather than dividing by an unknown interval.
   */
  fun sampleThroughput(nowMillis: Long): Throughput {
    val currentIn = inbound.get()
    val currentOut = outbound.get()
    val elapsedMs = nowMillis - lastSampleAt

    val result = if (lastSampleAt == 0L || elapsedMs <= 0L) {
      Throughput(0L, 0L)
    } else {
      Throughput(
        downloadBps = (currentIn - lastInbound) * 1000L / elapsedMs,
        outboundBps = (currentOut - lastOutbound) * 1000L / elapsedMs,
      )
    }

    lastSampleAt = nowMillis
    lastInbound = currentIn
    lastOutbound = currentOut
    return result
  }
}
