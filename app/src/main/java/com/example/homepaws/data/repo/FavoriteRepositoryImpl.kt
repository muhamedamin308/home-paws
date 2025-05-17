package com.example.homepaws.data.repo

import com.example.homepaws.data.model.local.FavoriteAnimalEntity
import com.example.homepaws.data.service.local.dao.FavoritesAnimalDao

/**
 * @author Muhamed Amin Hassan on 16,May,2025
 * @see <a href="https://github.com/muhamedamin308">Muhamed's Github</a>,
 * Egypt, Cairo.
 */
class FavoriteRepositoryImpl(
    private val favoritesDao: FavoritesAnimalDao,
) : FavoriteRepository {
    override suspend fun addFavorite(animal: FavoriteAnimalEntity) {
        favoritesDao.insertFavorite(animal)
    }

    override suspend fun removeFavorite(animal: FavoriteAnimalEntity) {
        favoritesDao.deleteFavorite(animal)
    }

    override suspend fun getAllFavorites(): List<FavoriteAnimalEntity> =
        favoritesDao.getAllFavorites()

    override suspend fun isFavorite(animalId: Int): Boolean =
        favoritesDao.isFavorite(animalId)

}