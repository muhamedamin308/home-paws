package com.example.homepaws.data.repo

import com.example.homepaws.data.model.animal.Animal
import com.example.homepaws.data.model.animal.AnimalResponse
import com.example.homepaws.data.model.breeds.BreedsResponse
import com.example.homepaws.data.model.organization.OrganizationResponse
import com.example.homepaws.data.model.types.TypeResponse
import com.example.homepaws.data.service.api.AnimalApiService
import com.example.homepaws.state.AppState
import com.example.homepaws.utils.NetworkUtils.safeApiCall

/**
 * @author Muhamed Amin Hassan on 13,May,2025
 * @see <a href="https://github.com/muhamedamin308">Muhamed's Github</a>,
 * Egypt, Cairo.
 */

class AnimalRepositoryImpl(
    private val apiService: AnimalApiService,
) : AnimalRepository {
    override suspend fun getAnimals(): AppState<AnimalResponse> = safeApiCall {
        apiService.getAnimals()
    }

    override suspend fun searchPets(
        type: String?,
        breed: String?,
        size: String?,
        gender: String?,
        age: String?,
        status: String?,
        location: String?,
        distance: Int?,
        sort: String?,
        page: Int,
    ): AppState<AnimalResponse> = safeApiCall {
        apiService.searchPets(
            type, breed, size, gender, age,
            status, location, distance, sort, page
        )
    }

    override suspend fun getAnimalById(id: Int): AppState<Animal> = safeApiCall {
        apiService.getAnimalById(id)
    }

    override suspend fun getAnimalTypes(): AppState<TypeResponse> = safeApiCall {
        apiService.getAnimalTypes()
    }

    override suspend fun getBreeds(type: String): AppState<BreedsResponse> = safeApiCall {
        apiService.getBreeds(type)
    }

    override suspend fun getOrganizations(): AppState<OrganizationResponse> = safeApiCall {
        apiService.getOrganizations()
    }

    override suspend fun getAnimalsInOrganization(orgId: String): AppState<AnimalResponse> =
        safeApiCall {
            apiService.getAnimalsInOrganization(orgId)
        }
}