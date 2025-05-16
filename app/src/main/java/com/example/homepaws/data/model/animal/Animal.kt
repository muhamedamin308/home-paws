package com.example.homepaws.data.model.animal

import com.example.homepaws.data.model.appearance.Colors
import com.example.homepaws.data.model.context.Contact
import com.example.homepaws.data.model.context.Environment
import com.example.homepaws.data.model.navigation.Links
import com.example.homepaws.data.model.media.Photo
import com.example.homepaws.data.model.media.PrimaryPhotoCropped
import com.example.homepaws.data.model.media.Video
import com.example.homepaws.data.model.core.Attributes
import com.fasterxml.jackson.annotation.JsonProperty

data class Animal(
    val id: Long,
    @JsonProperty("organization_id")
    val organizationId: String,
    val url: String,
    val type: String,
    val species: String,
    val breeds: Breeds,
    val colors: Colors,
    val age: String,
    val gender: String,
    val size: String,
    val coat: String?,
    val attributes: Attributes,
    val environment: Environment,
    val tags: List<String>,
    val name: String,
    val description: String?,
    @JsonProperty("organization_animal_id")
    val organizationAnimalId: String?,
    val photos: List<Photo>,
    @JsonProperty("primary_photo_cropped")
    val primaryPhotoCropped: PrimaryPhotoCropped?,
    val videos: List<Video>,
    val status: String,
    @JsonProperty("status_changed_at")
    val statusChangedAt: String,
    @JsonProperty("published_at")
    val publishedAt: String,
    val distance: Double,
    val contact: Contact,
    @JsonProperty("_links")
    val links: Links,
)