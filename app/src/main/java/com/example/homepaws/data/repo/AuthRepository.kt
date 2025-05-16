package com.example.homepaws.data.repo

import com.example.homepaws.data.model.response.AuthResponse
import com.example.homepaws.state.AppState

/**
 * @author Muhamed Amin Hassan on 16,May,2025
 * @see <a href="https://github.com/muhamedamin308">Muhamed's Github</a>,
 * Egypt, Cairo.
 */
interface AuthRepository {
    suspend fun fetchAccessToken(): AppState<AuthResponse>
}