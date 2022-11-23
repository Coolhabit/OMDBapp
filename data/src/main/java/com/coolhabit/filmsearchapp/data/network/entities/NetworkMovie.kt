package com.coolhabit.filmsearchapp.data.network.entities

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class NetworkMovie(
    @SerialName("Poster")
    val Poster: String,
    @SerialName("Title")
    val Title: String,
    @SerialName("Type")
    val Type: String,
    @SerialName("Year")
    val Year: String,
    @SerialName("imdbID")
    val imdbID: String
)
