package com.example.homepaws.data.model.navigation

import com.example.homepaws.data.model.organization.Organization
import com.example.homepaws.data.model.appearance.Type
import com.example.homepaws.data.model.core.Self

data class Links(
    val organization: Organization,
    val self: Self,
    val type: Type
)