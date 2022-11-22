package com.coolhabit.filmsearchapp.domain.entities

data class Movie(
    val imdbId: String,
    val name: String,
    val poster: String,
    val year: String,
    val type: String,
    var isFavorite: Boolean,
)
