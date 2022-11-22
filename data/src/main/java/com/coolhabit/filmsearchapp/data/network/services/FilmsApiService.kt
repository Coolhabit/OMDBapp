package com.coolhabit.filmsearchapp.data.network.services

import com.coolhabit.filmsearchapp.data.network.FilmsApi
import com.coolhabit.filmsearchapp.data.network.mappers.toDomain
import com.coolhabit.filmsearchapp.domain.api.IMoviesApiService
import com.coolhabit.filmsearchapp.domain.entities.Movie

class FilmsApiService(private val api: FilmsApi) : IMoviesApiService {
    override suspend fun searchMoviesByName(query: String?): List<Movie> {
        return api.getMovies(query).Search.map { it.toDomain() }
    }

    override suspend fun getMovieById(movieId: String?): Movie {
        return api.getMovieDetails(movieId).toDomain()
    }
}
