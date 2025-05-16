package com.example.homepaws.data.model.animal

import com.example.homepaws.data.model.navigation.Pagination

data class AnimalResponse(
    val animals: List<Animal>,
    val pagination: Pagination,
)