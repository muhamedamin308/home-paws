package com.example.homepaws.features.home.viewmodel

import com.example.homepaws.data.model.animal.Animal
import com.example.homepaws.data.repo.AnimalRepository
import com.example.homepaws.data.repo.FavoriteRepository
import com.example.homepaws.features.base.BaseViewModel
import com.example.homepaws.state.AppState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

/**
 * @author Muhamed Amin Hassan on 16,May,2025
 * @see <a href="https://github.com/muhamedamin308">Muhamed's Github</a>,
 * Egypt, Cairo.
 */

class PetDetailsViewModel(
    private val animalRepository: AnimalRepository,
    private val databaseRepository: FavoriteRepository
): BaseViewModel() {
    private val _isFavorite = MutableStateFlow(false)
    val isFavorite = _isFavorite.asStateFlow()

    private val _petDetails =
        MutableStateFlow<AppState<Animal>>(AppState.Ideal())
    val petDetails = _petDetails.asStateFlow()


}