package com.coolhabit.filmsearchapp.data.network.entities

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class NetworkMovieDetails(
    @SerialName("Actors")
    val Actors: String,
    @SerialName("Awards")
    val Awards: String,
    @SerialName("BoxOffice")
    val BoxOffice: String,
    @SerialName("Country")
    val Country: String,
    @SerialName("DVD")
    val DVD: String,
    @SerialName("Director")
    val Director: String,
    @SerialName("Genre")
    val Genre: String,
    @SerialName("Language")
    val Language: String,
    @SerialName("Metascore")
    val Metascore: String,
    @SerialName("Plot")
    val Plot: String,
    @SerialName("Poster")
    val Poster: String,
    @SerialName("Production")
    val Production: String,
    @SerialName("Rated")
    val Rated: String,
    @SerialName("Ratings")
    val Ratings: List<NetworkRating>,
    @SerialName("Released")
    val Released: String,
    @SerialName("Response")
    val Response: String,
    @SerialName("Runtime")
    val Runtime: String,
    @SerialName("Title")
    val Title: String,
    @SerialName("Type")
    val Type: String,
    @SerialName("Website")
    val Website: String,
    @SerialName("Writer")
    val Writer: String,
    @SerialName("Year")
    val Year: String,
    @SerialName("imdbID")
    val imdbID: String,
    @SerialName("imdbRating")
    val imdbRating: String,
    @SerialName("imdbVotes")
    val imdbVotes: String
)
