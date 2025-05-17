package com.example.homepaws.data.service.api

import com.example.homepaws.data.model.animal.Animal
import com.example.homepaws.data.model.animal.AnimalResponse
import com.example.homepaws.data.model.breeds.BreedsResponse
import com.example.homepaws.data.model.enums.Gender
import com.example.homepaws.data.model.enums.SortOption
import com.example.homepaws.data.model.organization.OrganizationResponse
import com.example.homepaws.data.model.types.TypeResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

/**
 * @author Muhamed Amin Hassan on 13,May,2025
 * @see <a href="https://github.com/muhamedamin308">Muhamed's Github</a>,
 * Egypt, Cairo.
 */
interface AnimalApiService {

    companion object {
        const val DEFAULT_DISTANCE = 100
        const val DEFAULT_SORT = "recent"
    }

    @GET("v2/animals")
    suspend fun getAnimals(): Response<AnimalResponse>

    @GET("v2/animals")
    suspend fun searchPets(
        @Query("type") type: String? = null,
        @Query("breed") breed: String? = null,
        @Query("size") size: String? = null,
        @Query("gender") gender: Gender? = null,
        @Query("age") age: String? = null,
        @Query("status") status: String? = null,
        @Query("location") location: String? = null,
        @Query("distance") distance: Int? = DEFAULT_DISTANCE,
        @Query("sort") sort: SortOption? = null,
        @Query("page") page: Int = 1,
    ): Response<AnimalResponse>

    @GET("v2/animals/{id}")
    suspend fun getAnimalById(
        @Path("id") id: Int,
    ): Response<Animal>

    @GET("v2/types")
    suspend fun getAnimalTypes(): Response<TypeResponse>

    @GET("v2/types/{type}/breeds")
    suspend fun getBreeds(
        @Path("type") type: String,
    ): Response<BreedsResponse>

    @GET("v2/organizations")
    suspend fun getOrganizations(): Response<OrganizationResponse>

    // animals in specific organization
    @GET("v2/animals")
    suspend fun getAnimalsInOrganization(
        @Query("organization") organization: String,
    ): Response<AnimalResponse>

}