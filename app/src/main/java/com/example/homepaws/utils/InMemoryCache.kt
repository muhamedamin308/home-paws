package com.example.homepaws.utils

import android.content.SharedPreferences
import androidx.core.content.edit

/**
 * @author Muhamed Amin Hassan on 16,May,2025
 * @see <a href="https://github.com/muhamedamin308">Muhamed's Github</a>,
 * Egypt, Cairo.
 */
object InMemoryCache {

    class TokenStorage(private val preferences: SharedPreferences) {

        companion object {
            private const val ACCESS_TOKEN = "access_token"
            private const val EXPIRES_IN = "expires_in"
        }

        private var token: String? = null
        private var expiresAt: Long = 0

        fun saveToken(token: String?, expiresInSeconds: Long) {
            if (token.isNullOrEmpty()) return  // don't proceed with null/empty tokens

            val expiry = System.currentTimeMillis() + expiresInSeconds * 1000
            this.token = token
            this.expiresAt = expiry
            preferences.edit {
                putString(ACCESS_TOKEN, token)
                putLong(EXPIRES_IN, expiry)
            }
        }


        fun getToken(): String? {
            if (token == null) {
                token = preferences.getString(ACCESS_TOKEN, null)
                expiresAt = preferences.getLong(EXPIRES_IN, 0)
            }
            return if (System.currentTimeMillis() < expiresAt) token else null
        }

    }
}
