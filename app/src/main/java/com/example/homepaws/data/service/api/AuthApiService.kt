package com.example.homepaws.data.service.api

import com.example.homepaws.data.model.response.AuthResponse
import com.example.homepaws.utils.ApiConfig
import retrofit2.Response
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

/**
 * @author Muhamed Amin Hassan on 13,May,2025
 * @see <a href="https://github.com/muhamedamin308">Muhamed's Github</a>,
 * Egypt, Cairo.
 */
interface AuthApiService {
    @FormUrlEncoded
    @POST("v2/oauth2/token")
    suspend fun getAccessToken(
        @Field("grant_type") grantType: String = "client_credentials",
        @Field("client_id") clientId: String = ApiConfig.CLIENT_ID,
        @Field("client_secret") clientSecret: String = ApiConfig.CLIENT_SECRET,
    ): Response<AuthResponse>
}