package com.example.homepaws.data.model.organization

data class OrganizationResponse(
    val organizations: List<OrganizationX>,
    val pagination: Pagination
)