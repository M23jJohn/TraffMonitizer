package com.example.node

import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

/**
 * Load-balancer resolution.
 *
 * Reproduces the reference SDK's `com.tm.R0` / `com.tm.S0`:
 *   GET https://<balancerHost>/resolve  (5s connect timeout, 5s read timeout)
 * The response body's first line is the hostname of the edge server that the
 * node should open its TCP session against, on [NodeConfig.apiPort].
 *
 * TLS is used exactly as the platform provides it — no custom TrustManager,
 * no hostname-verifier override.
 */
internal object LoadBalancerResolver {

  class ResolveException(message: String, cause: Throwable? = null) : IOException(message, cause)

  suspend fun resolve(balancerHost: String): String = withContext(Dispatchers.IO) {
    val url = URL("https://$balancerHost/resolve")
    var connection: HttpURLConnection? = null
    try {
      connection = (url.openConnection() as HttpURLConnection).apply {
        connectTimeout = TIMEOUT_MS
        readTimeout = TIMEOUT_MS
        requestMethod = "GET"
      }
      val code = connection.responseCode
      if (code != HttpURLConnection.HTTP_OK) {
        throw ResolveException("Load balancer returned HTTP $code")
      }
      val line = connection.inputStream.use { stream ->
        BufferedReader(InputStreamReader(stream, Charsets.UTF_8)).readLine()
      }
      val host = line?.trim()
      if (host.isNullOrEmpty()) {
        throw ResolveException("Load balancer returned an empty server address")
      }
      host
    } catch (e: ResolveException) {
      throw e
    } catch (e: Exception) {
      throw ResolveException("Load balancer request to $balancerHost failed: ${e.javaClass.simpleName}", e)
    } finally {
      connection?.disconnect()
    }
  }

  private const val TIMEOUT_MS = 5_000
}
