package com.example.homepaws.data.repo

import com.example.homepaws.data.model.local.FavoriteAnimalEntity

/**
 * @author Muhamed Amin Hassan on 16,May,2025
 * @see <a href="https://github.com/muhamedamin308">Muhamed's Github</a>,
 * Egypt, Cairo.
 */
interface FavoriteRepository {
    suspend fun addFavorite(animal: FavoriteAnimalEntity)
    suspend fun removeFavorite(animal: FavoriteAnimalEntity)
    suspend fun getAllFavorites(): List<FavoriteAnimalEntity>
    suspend fun isFavorite(animalId: Int): Boolean
}