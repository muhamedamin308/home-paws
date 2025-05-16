package com.example.homepaws.data.repo

import com.example.homepaws.data.model.animal.Animal
import com.example.homepaws.data.model.animal.AnimalResponse
import com.example.homepaws.data.model.breeds.BreedsResponse
import com.example.homepaws.data.model.organization.OrganizationResponse
import com.example.homepaws.data.model.types.TypeResponse
import com.example.homepaws.state.AppState

/**
 * @author Muhamed Amin Hassan on 16,May,2025
 * @see <a href="https://github.com/muhamedamin308">Muhamed's Github</a>,
 * Egypt, Cairo.
 */
interface AnimalRepository {
    suspend fun getAnimals(): AppState<AnimalResponse>
    suspend fun searchPets(
        type: String? = null,
        breed: String? = null,
        size: String? = null,
        gender: String? = null,
        age: String? = null,
        status: String? = "adoptable",
        location: String? = null,
        distance: Int? = 100,
        sort: String? = "recent",
        page: Int = 1,
    ): AppState<AnimalResponse>

    suspend fun getAnimalById(id: Int): AppState<Animal>
    suspend fun getAnimalTypes(): AppState<TypeResponse>
    suspend fun getBreeds(type: String): AppState<BreedsResponse>
    suspend fun getOrganizations(): AppState<OrganizationResponse>
    suspend fun getAnimalsInOrganization(orgId: String): AppState<AnimalResponse>
}