package com.example.homepaws.features.home.viewmodel

import androidx.lifecycle.viewModelScope
import com.example.homepaws.data.model.animal.AnimalResponse
import com.example.homepaws.data.model.organization.OrganizationResponse
import com.example.homepaws.data.model.types.TypeResponse
import com.example.homepaws.data.repo.AnimalRepository
import com.example.homepaws.data.service.firebase.AuthenticationService
import com.example.homepaws.features.base.BaseViewModel
import com.example.homepaws.state.AppState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
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

    private val _authorizationState =
        MutableStateFlow<AppState<String>>(AppState.Ideal())
    val authorizationState = _authorizationState.asStateFlow()

    private val _categoriesState =
        MutableStateFlow<AppState<TypeResponse>>(AppState.Ideal())
    val categoriesState = _categoriesState.asStateFlow()

    private val _commonOrganizationsState =
        MutableStateFlow<AppState<OrganizationResponse>>(AppState.Ideal())
    val commonOrganizationState = _commonOrganizationsState.asStateFlow()

    private val _animalsState =
        MutableStateFlow<AppState<AnimalResponse>>(AppState.Ideal())
    val animalsState = _animalsState.asStateFlow()

    init {
        fetchCategories()
        fetchCommonOrganizations()
        fetchAnimals()
    }

    private fun fetchCategories() {
        viewModelScope.launch {
            updateApiState(_categoriesState, AppState.Loading())
            updateApiState(_categoriesState, animalRepository.getAnimalTypes())
        }
    }

    private fun fetchCommonOrganizations() {
        viewModelScope.launch {
            updateApiState(_commonOrganizationsState, AppState.Loading())
            updateApiState(
                _commonOrganizationsState,
                animalRepository.getOrganizations()
            )
        }
    }

    private fun fetchAnimals() {
        viewModelScope.launch {
            updateApiState(_animalsState, AppState.Loading())
            updateApiState(_animalsState, animalRepository.getAnimals())
        }
    }

    private suspend fun <T> updateApiState(
        stateFlow: MutableStateFlow<AppState<T>>,
        result: AppState<T>,
    ) {
        stateFlow.emit(result)
    }
}