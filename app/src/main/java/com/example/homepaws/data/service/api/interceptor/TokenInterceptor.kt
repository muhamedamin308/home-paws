package com.example.homepaws.data.service.api.interceptor

import okhttp3.Interceptor
import okhttp3.Response

/**
 * @author Muhamed Amin Hassan on 16,May,2025
 * @see <a href="https://github.com/muhamedamin308">Muhamed's Github</a>,
 * Egypt, Cairo.
 */
class TokenInterceptor(
    private val tokenProvider: () -> String? // Now accepts null
) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()
        val token = tokenProvider()

        return if (token != null) {
            val newRequest = originalRequest.newBuilder()
                .addHeader("Authorization", "Bearer $token")
                .build()
            chain.proceed(newRequest)
        } else {
            chain.proceed(originalRequest)
        }
    }
}