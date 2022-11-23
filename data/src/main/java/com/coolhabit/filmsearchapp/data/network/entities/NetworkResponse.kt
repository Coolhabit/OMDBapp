package com.coolhabit.filmsearchapp.data.network.entities

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class NetworkResponse(
    @SerialName("Response")
    val Response: String,
    @SerialName("Search")
    val Search: List<NetworkMovie>,
    @SerialName("totalResults")
    val totalResults: String
)
