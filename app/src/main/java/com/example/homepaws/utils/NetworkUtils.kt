package com.example.homepaws.utils

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import com.example.homepaws.state.AppState
import retrofit2.Response
import java.io.IOException

/**
 * @author Muhamed Amin Hassan on 16,May,2025
 * @see <a href="https://github.com/muhamedamin308">Muhamed's Github</a>,
 * Egypt, Cairo.
 */

object NetworkUtils {
    inline fun <reified T> safeApiCall(apiCall: () -> Response<T>): AppState<T> = try {
        val response = apiCall()
        if (response.isSuccessful) {
            response.body()?.let { AppState.Success(it) }
                ?: AppState.Error("Empty response body")
        } else {
            AppState.Error("HTTP ${response.code()}: ${response.message()}")
        }
    } catch (e: Exception) {
        AppState.Error(e.message ?: "Unknown error occurred")
    }

    fun isNetworkAvailable(context: Context): Boolean {
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val network = connectivityManager.activeNetwork ?: return false
        val capabilities =
            connectivityManager.getNetworkCapabilities(network) ?: return false

        return capabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
    }

    class NoInternetException(message: String) : IOException(message)
}