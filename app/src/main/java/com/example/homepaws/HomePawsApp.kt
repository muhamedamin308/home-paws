package com.example.homepaws

import android.app.Application
import com.example.homepaws.di.firebaseModule
import com.example.homepaws.di.localStorageModule
import com.example.homepaws.di.networkModule
import com.example.homepaws.di.repositoryModule
import com.example.homepaws.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

/**
 * @author Muhamed Amin Hassan on 13,May,2025
 * @see <a href="https://github.com/muhamedamin308">Muhamed's Github</a>,
 * Egypt, Cairo.
 */
class HomePawsApp : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger()
            androidContext(androidContext = this@HomePawsApp)
            modules(
                listOf(
                    viewModelModule,
                    networkModule,
                    firebaseModule,
                    localStorageModule,
                    repositoryModule
                )
            )
        }
    }
}