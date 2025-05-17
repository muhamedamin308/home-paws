package com.example.homepaws.data.model.local

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * @author Muhamed Amin Hassan on 16,May,2025
 * @see <a href="https://github.com/muhamedamin308">Muhamed's Github</a>,
 * Egypt, Cairo.
 */

@Entity(tableName = "favorites")
data class FavoriteAnimalEntity(
    @PrimaryKey val id: Int,
    val name: String,
    val photoUrl: String?,
    val type: String,
    val breed: String?,
    val age: String,
    val gender: String,
)
