package com.example.homepaws.data.model.response

import com.google.gson.annotations.SerializedName

data class AuthResponse(
    @SerializedName("access_token")
    val accessToken: String,
    @SerializedName("expires_in")
    val expiresIn: Int,
    @SerializedName("token_type")
    val tokenType: String,
)