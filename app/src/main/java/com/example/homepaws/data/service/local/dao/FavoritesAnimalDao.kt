package com.example.homepaws.data.service.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.homepaws.data.model.local.FavoriteAnimalEntity

/**
 * @author Muhamed Amin Hassan on 16,May,2025
 * @see <a href="https://github.com/muhamedamin308">Muhamed's Github</a>,
 * Egypt, Cairo.
 */
@Dao
interface FavoritesAnimalDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFavorite(animal: FavoriteAnimalEntity)

    @Delete
    suspend fun deleteFavorite(animal: FavoriteAnimalEntity)

    @Query("SELECT * FROM favorites")
    suspend fun getAllFavorites(): List<FavoriteAnimalEntity>

    @Query("SELECT EXISTS(SELECT 1 FROM favorites WHERE id = :animalId)")
    suspend fun isFavorite(animalId: Int): Boolean
}