package com.example.homepaws.di

import com.example.homepaws.data.service.api.AnimalApiService
import com.example.homepaws.data.service.api.AuthApiService
import com.example.homepaws.data.service.api.interceptor.NetworkConnectivityInterceptor
import com.example.homepaws.data.service.api.interceptor.TokenInterceptor
import com.example.homepaws.utils.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * @author Muhamed Amin Hassan on 13,May,2025
 * @see <a href="https://github.com/muhamedamin308">Muhamed's Github</a>,
 * Egypt, Cairo.
 */

val networkModule = module {

    single { NetworkConnectivityInterceptor(get()) }

    single {
        TokenInterceptor {
            get<Cache.TokenStorage>().getToken() ?: ""
        }
    }

    single {
        OkHttpClient.Builder()
            .addInterceptor(get<NetworkConnectivityInterceptor>())
            .addInterceptor(get<TokenInterceptor>())
            .addInterceptor(HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            })
            .build()
    }

    single {
        Retrofit.Builder()
            .baseUrl("https://api.petfinder.com/")
            .client(get())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    single<AuthApiService> { get<Retrofit>().create(AuthApiService::class.java) }

    single<AnimalApiService> { get<Retrofit>().create(AnimalApiService::class.java) }
}
