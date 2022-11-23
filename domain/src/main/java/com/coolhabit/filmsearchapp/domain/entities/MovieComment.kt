package com.coolhabit.filmsearchapp.domain.entities

data class MovieComment(
    val movieId: String,
    var commentsList: List<String>,
)
