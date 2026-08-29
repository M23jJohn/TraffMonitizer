package com.example.node

import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities
import android.net.NetworkRequest
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.channelFlow
import kotlinx.coroutines.flow.distinctUntilChanged

/**
 * Network availability + transport type, as the reference SDK observes it.
 *
 * The reference (`com.tm.A0`) registers a `NetworkCallback` against an
 * unfiltered `NetworkRequest` and checks `NetworkCapabilities.hasTransport(TRANSPORT_WIFI)`
 * to satisfy its `wifiOnly` configuration flag.
 */
internal data class NetworkState(
  val connected: Boolean,
  val wifi: Boolean,
) {
  companion object {
    val Disconnected = NetworkState(connected = false, wifi = false)
  }
}

internal class ConnectivityMonitor(context: Context) {

  private val connectivityManager =
    context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

  fun current(): NetworkState {
    val network = connectivityManager.activeNetwork ?: return NetworkState.Disconnected
    val caps = connectivityManager.getNetworkCapabilities(network) ?: return NetworkState.Disconnected
    return NetworkState(
      connected = caps.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET),
      wifi = caps.hasTransport(NetworkCapabilities.TRANSPORT_WIFI),
    )
  }

  /** Emits on every capability/availability change, plus the current state up front. */
  fun states(): Flow<NetworkState> = channelFlow {
    trySend(current())
    val callback = object : ConnectivityManager.NetworkCallback() {
      override fun onAvailable(network: Network) {
        trySend(current())
      }

      override fun onLost(network: Network) {
        trySend(current())
      }

      override fun onCapabilitiesChanged(network: Network, caps: NetworkCapabilities) {
        trySend(current())
      }
    }
    val request = NetworkRequest.Builder().build()
    connectivityManager.registerNetworkCallback(request, callback)
    awaitClose { runCatching { connectivityManager.unregisterNetworkCallback(callback) } }
  }.distinctUntilChanged()
}
