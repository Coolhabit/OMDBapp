package com.coolhabit.filmsearchapp.data.network.entities

data class NetworkResponse(
    val Response: String,
    val Search: List<NetworkMovie>,
    val totalResults: String
)
