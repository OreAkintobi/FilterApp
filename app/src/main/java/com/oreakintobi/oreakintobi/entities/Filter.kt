package com.oreakintobi.oreakintobi.entities

import kotlinx.serialization.Serializable

@Serializable
data class Filter(
    val accounts: List<Account>
)

@Serializable
data class Account(
    val id: String,
    val avatar: String,
    val fullName: String,
    val createdAt: String,
    val gender: String,
    val colors: List<String>,
    val countries: List<String>
)

