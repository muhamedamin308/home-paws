package com.example.homepaws.data.model.context

import com.example.homepaws.data.model.organization.Address

data class Contact(
    val address: Address,
    val email: String,
    val phone: String
)