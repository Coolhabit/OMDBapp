package com.coolhabit.filmsearchapp.domain.api

import com.coolhabit.filmsearchapp.domain.entities.Movie

interface IMoviesApiService {

    suspend fun searchMovies(query: String?, pages: Int?): List<Movie>

    suspend fun getMovieById(movieId: String?): Movie
}
