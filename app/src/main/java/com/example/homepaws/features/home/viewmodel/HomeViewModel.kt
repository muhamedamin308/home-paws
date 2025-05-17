package com.example.homepaws.features.home.viewmodel

import androidx.lifecycle.viewModelScope
import com.example.homepaws.data.model.enums.Gender
import com.example.homepaws.data.model.enums.SortOption
import com.example.homepaws.data.repo.AnimalRepository
import com.example.homepaws.data.service.firebase.AuthenticationService
import com.example.homepaws.features.base.BaseViewModel
import com.example.homepaws.state.AppState
import kotlinx.coroutines.Job
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

/**
 * @author Muhamed Amin Hassan on 16,May,2025
 * @see <a href="https://github.com/muhamedamin308">Muhamed's Github</a>,
 * Egypt, Cairo.
 */
class HomeViewModel(
    private val authService: AuthenticationService,
    private val animalRepository: AnimalRepository,
) : BaseViewModel() {

    sealed interface UiState {
        data class Data<T>(val value: T) : UiState
        object Loading : UiState
        object Ideal : UiState
        data class Error(val message: String) : UiState
    }

    private val _uiState = MutableStateFlow<Map<String, UiState>>(emptyMap())
    val uiState = _uiState.asStateFlow()

    init {
        loadInitialData()
    }

    private fun loadInitialData() = viewModelScope.launch {
        launchParallel(
            { fetchAndPost("categories") { animalRepository.getAnimalTypes() } },
            { fetchAndPost("organizations") { animalRepository.getOrganizations() } },
            { fetchAndPost("animals") { animalRepository.getAnimals() } },
            { fetchUsername() }
        )
    }

    fun fetchUsername() {
        updateState("username", UiState.Loading)
        updateState("username", UiState.Data(authService.getCurrentUser()))
    }

    fun getPetDetails(id: Int) = fetchAndPost("petDetails") {
        animalRepository.getAnimalById(id)
    }

    fun getPetBreeds(type: String) = fetchAndPost("petBreeds") {
        animalRepository.getBreeds(type)
    }

    fun searchPets(
        type: String? = null,
        breed: String? = null,
        size: String? = null,
        gender: Gender? = null,
        age: String? = null,
        status: String? = "adoptable",
        location: String? = null,
        distance: Int? = 100,
        sort: SortOption? = null,
        page: Int = 1,
    ) = fetchAndPost("searchPets") {
        animalRepository.searchPets(
            type,
            breed,
            size,
            gender,
            age,
            status,
            location,
            distance,
            sort,
            page
        )
    }

    fun filterAnimalsByOrg(orgId: String) = fetchAndPost("filteredPets") {
        animalRepository.getAnimalsInOrganization(orgId)
    }

    private fun <T> AppState<T>.toUiState(): UiState = when (this) {
        is AppState.Success -> UiState.Data(data)
        is AppState.Error -> UiState.Error(message ?: "Unknown error")
        is AppState.Loading -> UiState.Loading
        is AppState.Ideal -> UiState.Ideal
    }

    private fun updateState(key: String, state: UiState) {
        _uiState.update { it.toMutableMap().apply { put(key, state) } }
    }

    private fun fetchAndPost(key: String, request: suspend () -> AppState<*>): Job =
        viewModelScope.launch {
            updateState(key, UiState.Loading)
            try {
                updateState(key, request().toUiState())
            } catch (e: Exception) {
                updateState(key, UiState.Error(e.message ?: "Unexpected error"))
            }
        }

    private fun launchParallel(vararg blocks: suspend () -> Unit) = viewModelScope.launch {
        blocks.map { async { it() } }.awaitAll()
    }
}
