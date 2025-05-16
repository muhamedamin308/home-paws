package com.example.homepaws.data.model.navigation

import com.fasterxml.jackson.annotation.JsonProperty

data class Pagination(
    @JsonProperty("count_per_page")
    val countPerPage: Long,
    @JsonProperty("total_count")
    val totalCount: Long,
    @JsonProperty("current_page")
    val currentPage: Long,
    @JsonProperty("total_pages")
    val totalPages: Long,
    @JsonProperty("_links")
    val links: Links2,
)