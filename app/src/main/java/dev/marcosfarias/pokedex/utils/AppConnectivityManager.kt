package dev.marcosfarias.pokedex.utils

import android.content.Context
import java.io.IOException
import java.util.concurrent.atomic.AtomicBoolean
import javax.net.ssl.SSLException

data class AppConnectivityResult(
    val isReachable: Boolean,
    val isAppDomainAvailable: Boolean,
    val reachedEndpoint: String?,
    val isExtremeFallbackUsed: Boolean,
    val diagnosticMessage: String
)

class AppConnectivityManager {

    companion object {
        val shared: AppConnectivityManager by lazy { AppConnectivityManager() }
    }

    private val generalDiagnostic by lazy { ConnectivityAndInternetAccess.Builder().build() }
    private val diagnosticInFlight = AtomicBoolean(false)

    /**
     * Performs only the cheap local precheck. It deliberately does not perform DNS,
     * TCP, TLS or HTTP traffic. The real operation decides whether its service works.
     */
    fun checkConnectivity(
        context: Context,
        onResult: (AppConnectivityResult) -> Unit
    ) {
        val connected = canStartRemoteRequest(context)
        onResult(
            AppConnectivityResult(
                isReachable = connected,
                isAppDomainAvailable = false,
                reachedEndpoint = null,
                isExtremeFallbackUsed = false,
                diagnosticMessage = if (connected) {
                    "Red utilizable disponible; se ejecutará la operación real."
                } else {
                    "Sin red utilizable; operación omitida y se usará el modo offline."
                }
            )
        )
    }

    /**
     * Runs the active general diagnostic only after a network-shaped operation failure.
     * HTTP responses are not passed here because they already prove server communication.
     */
    fun diagnoseNetworkFailure(
        context: Context,
        failure: Throwable,
        onResult: (AppConnectivityResult) -> Unit = {}
    ): ConnectivityAndInternetAccess.Request? {
        if (!isNetworkFailure(failure)) return null

        if (!canStartRemoteRequest(context)) {
            onResult(offlineResult())
            return null
        }

        if (!diagnosticInFlight.compareAndSet(false, true)) return null

        return generalDiagnostic.checkInternetAsync(context) { result ->
            diagnosticInFlight.set(false)
            onResult(
                AppConnectivityResult(
                    isReachable = result.reachable,
                    isAppDomainAvailable = false,
                    reachedEndpoint = result.reachedHost,
                    isExtremeFallbackUsed = true,
                    diagnosticMessage = if (result.reachable) {
                        "La red general tiene acceso a Internet; el servicio/target concreto ha fallado."
                    } else {
                        "La operación falló y no se ha confirmado acceso general a Internet."
                    }
                )
            )
        }
    }

    fun isNetworkFailure(failure: Throwable): Boolean {
        var cause: Throwable? = failure
        while (cause != null) {
            if (cause is IOException || cause is SSLException) return true
            cause = cause.cause
        }
        return false
    }

    fun canStartRemoteRequest(context: Context): Boolean =
        canStartRemoteRequest(
            ConnectivityAndInternetAccess.isConnected(context),
            ConnectivityAndInternetAccess.hasPhysicalNetwork(context)
        )

    internal fun canStartRemoteRequest(
        isConnected: Boolean,
        hasPhysicalNetwork: Boolean
    ): Boolean = isConnected && hasPhysicalNetwork

    private fun offlineResult() = AppConnectivityResult(
        isReachable = false,
        isAppDomainAvailable = false,
        reachedEndpoint = null,
        isExtremeFallbackUsed = false,
        diagnosticMessage = "Sin red utilizable; operación omitida y se usará el modo offline."
    )

    fun observeNetwork(
        context: Context,
        callback: ConnectivityAndInternetAccess.NetworkStateCallback
    ): ConnectivityAndInternetAccess.NetworkObserver {
        return ConnectivityAndInternetAccess.observeNetwork(context, callback)
    }
}
