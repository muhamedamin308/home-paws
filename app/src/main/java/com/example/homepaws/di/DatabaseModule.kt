package com.example.homepaws.di

import androidx.room.Room
import com.example.homepaws.data.repo.FavoriteRepositoryImpl
import com.example.homepaws.data.service.local.database.AppDatabase
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

/**
 * @author Muhamed Amin Hassan on 16,May,2025
 * @see <a href="https://github.com/muhamedamin308">Muhamed's Github</a>,
 * Egypt, Cairo.
 */

val databaseModule = module {

    single<AppDatabase> {
        Room.databaseBuilder(
            androidContext(),
            AppDatabase::class.java,
            "homepaws-db"
        ).build()
    }

    single { get<AppDatabase>().favoritesDao() }

    single { FavoriteRepositoryImpl(get()) }
}