package com.coolhabit.filmsearchapp.data.network.mappers

import com.coolhabit.filmsearchapp.data.network.entities.NetworkMovie
import com.coolhabit.filmsearchapp.data.network.entities.NetworkMovieDetails
import com.coolhabit.filmsearchapp.domain.entities.Movie

fun NetworkMovie.toDomain(): Movie = Movie(
    imdbId = this.imdbID,
    name = this.Title,
    year = this.Year,
    poster = this.Poster,
    type = this.Type,
    isFavorite = false,
    commentsList = emptyList(),
)

fun NetworkMovieDetails.toDomain(): Movie = Movie(
    imdbId = this.imdbID,
    name = this.Title,
    year = this.Year,
    poster = this.Poster,
    type = this.Type,
    isFavorite = false,
    commentsList = emptyList(),
)
