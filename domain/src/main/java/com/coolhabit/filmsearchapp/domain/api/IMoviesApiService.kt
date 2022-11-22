package com.coolhabit.filmsearchapp.domain.api

import com.coolhabit.filmsearchapp.domain.entities.Movie

interface IMoviesApiService {

    suspend fun searchMoviesByName(query: String?): List<Movie>

    suspend fun getMovieById(movieId: String?): Movie
}
