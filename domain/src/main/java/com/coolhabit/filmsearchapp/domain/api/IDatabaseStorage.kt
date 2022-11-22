package com.coolhabit.filmsearchapp.domain.api

import com.coolhabit.filmsearchapp.domain.entities.Movie

interface IDatabaseStorage {

    suspend fun addMovieToFavorite(movie: Movie)

    suspend fun removeMovieFromFavorite(movie: Movie)

    suspend fun getFavoriteMovies(): List<Movie>
}
