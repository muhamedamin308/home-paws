package com.example.homepaws.data.model.organization

data class OrganizationX(
    val _links: Links,
    val address: AddressX,
    val adoption: Adoption,
    val distance: Any,
    val email: String,
    val hours: Hours,
    val id: String,
    val mission_statement: String,
    val name: String,
    val phone: String,
    val photos: List<Photo>,
    val social_media: SocialMedia,
    val url: String,
    val website: String
)