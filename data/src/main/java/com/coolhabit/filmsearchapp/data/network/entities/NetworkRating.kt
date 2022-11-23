package com.coolhabit.filmsearchapp.data.network.entities

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class NetworkRating(
    @SerialName("Source")
    val Source: String,
    @SerialName("Value")
    val Value: String
)
