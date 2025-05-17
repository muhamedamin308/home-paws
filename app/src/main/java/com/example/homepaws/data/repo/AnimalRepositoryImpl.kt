package com.example.homepaws.data.repo

import com.example.homepaws.data.model.animal.Animal
import com.example.homepaws.data.model.animal.AnimalResponse
import com.example.homepaws.data.model.breeds.BreedsResponse
import com.example.homepaws.data.model.enums.Gender
import com.example.homepaws.data.model.enums.SortOption
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

// Repository implementation for animal-related API operations
class AnimalRepositoryImpl(
    private val apiService: AnimalApiService,
) : AnimalRepository {

    // Gets paginated list of all animals
    override suspend fun getAnimals(): AppState<AnimalResponse> = safeApiCall {
        apiService.getAnimals()
    }

    // Searches pets with multiple optional filters
    override suspend fun searchPets(
        type: String?, breed: String?, size: String?, gender: Gender?,
        age: String?, status: String?, location: String?,
        distance: Int?, sort: SortOption?, page: Int,
    ): AppState<AnimalResponse> = safeApiCall {
        apiService.searchPets(
            type = type,
            breed = breed,
            size = size,
            gender = gender,
            age = age,
            status = status,
            location = location,
            distance = distance,
            sort = sort,
            page = page
        )
    }

    // Gets details for a specific animal by ID
    override suspend fun getAnimalById(id: Int): AppState<Animal> = safeApiCall {
        apiService.getAnimalById(id)
    }

    // Fetches all available animal types (dog, cat, etc.)
    override suspend fun getAnimalTypes(): AppState<TypeResponse> = safeApiCall {
        apiService.getAnimalTypes()
    }

    // Gets breeds for a specific animal type
    override suspend fun getBreeds(type: String): AppState<BreedsResponse> = safeApiCall {
        apiService.getBreeds(type)
    }

    // Retrieves animal welfare organizations
    override suspend fun getOrganizations(): AppState<OrganizationResponse> = safeApiCall {
        apiService.getOrganizations()
    }

    // Gets animals available at specific organization
    override suspend fun getAnimalsInOrganization(orgId: String): AppState<AnimalResponse> =
        safeApiCall {
            apiService.getAnimalsInOrganization(orgId)
        }
}