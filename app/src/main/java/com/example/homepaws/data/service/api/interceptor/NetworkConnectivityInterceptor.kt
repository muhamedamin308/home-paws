package com.example.homepaws.data.service.api.interceptor

import android.content.Context
import com.example.homepaws.utils.NetworkUtils
import okhttp3.Interceptor
import okhttp3.Response

/**
 * @author Muhamed Amin Hassan on 16,May,2025
 * @see <a href="https://github.com/muhamedamin308">Muhamed's Github</a>,
 * Egypt, Cairo.
 */

class NetworkConnectivityInterceptor(
    private val context: Context,
) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        if (!NetworkUtils.isNetworkAvailable(context))
            throw NetworkUtils.NoInternetException("No internet connection. Please check your network")
        return chain.proceed(chain.request())
    }
}