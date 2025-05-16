package com.example.homepaws.di

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import com.example.homepaws.utils.Constants
import com.example.homepaws.utils.InMemoryCache
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

/**
 * @author Muhamed Amin Hassan on 14,May,2025
 * @see <a href="https://github.com/muhamedamin308">Muhamed's Github</a>,
 * Egypt, Cairo.
 */

val localStorageModule = module {
    single<SharedPreferences> {
        val application: Application = get()
        application.getSharedPreferences(
            Constants.SHARED_PREFERENCES_NAME,
            Application.MODE_PRIVATE
        )
    }

    single {
        androidContext()
            .getSharedPreferences("prefs", Context.MODE_PRIVATE)
    }

    single {
        InMemoryCache.TokenStorage(get())
    }
}