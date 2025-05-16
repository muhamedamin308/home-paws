package com.example.homepaws.data.repo

import com.example.homepaws.data.model.response.AuthResponse
import com.example.homepaws.data.service.api.AuthApiService
import com.example.homepaws.state.AppState
import com.example.homepaws.utils.NetworkUtils.safeApiCall

/**
 * @author Muhamed Amin Hassan on 16,May,2025
 * @see <a href="https://github.com/muhamedamin308">Muhamed's Github</a>,
 * Egypt, Cairo.
 */
class AuthRepositoryImpl(
    private val apiService: AuthApiService,
) : AuthRepository {
    override suspend fun fetchAccessToken(): AppState<AuthResponse> = safeApiCall {
        apiService.getAccessToken()
    }
}