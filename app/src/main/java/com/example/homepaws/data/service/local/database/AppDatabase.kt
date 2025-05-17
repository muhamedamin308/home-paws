package com.example.homepaws.data.service.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.homepaws.data.model.local.FavoriteAnimalEntity
import com.example.homepaws.data.service.local.dao.FavoritesAnimalDao

/**
 * @author Muhamed Amin Hassan on 16,May,2025
 * @see <a href="https://github.com/muhamedamin308">Muhamed's Github</a>,
 * Egypt, Cairo.
 */

@Database(entities = [FavoriteAnimalEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun favoritesDao(): FavoritesAnimalDao
}