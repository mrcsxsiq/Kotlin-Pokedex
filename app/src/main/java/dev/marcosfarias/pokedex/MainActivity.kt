package dev.marcosfarias.pokedex

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import dev.marcosfarias.pokedex.R
import dev.marcosfarias.pokedex.utils.ConnectivityAndInternetAccess

class MainActivity : AppCompatActivity() {

    private var networkObserver: ConnectivityAndInternetAccess.NetworkObserver? = null
    private var lastNetworkState: ConnectivityAndInternetAccess.NetworkState? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onStart() {
        super.onStart()
        networkObserver?.close()
        networkObserver = ConnectivityAndInternetAccess.observeNetwork(this) { state ->
            Log.d(
                "PokedexConnectivity",
                "Network State Changed: connected=${state.connected}, validated=${state.internetValidated}, captivePortal=${state.captivePortalDetected}"
            )
            val previous = lastNetworkState
            lastNetworkState = state
            if (previous == null || previous.connected == state.connected &&
                previous.captivePortalDetected == state.captivePortalDetected
            ) {
                return@observeNetwork
            }
            val message = when {
                state.captivePortalDetected -> getString(R.string.network_captive_portal)
                state.connected -> getString(R.string.network_recovered)
                else -> getString(R.string.network_disconnected)
            }
            Toast.makeText(applicationContext, message, Toast.LENGTH_SHORT).show()
        }
    }

    override fun onStop() {
        networkObserver?.close()
        networkObserver = null
        lastNetworkState = null
        super.onStop()
    }
}

