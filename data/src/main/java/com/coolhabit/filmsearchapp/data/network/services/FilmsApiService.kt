package com.coolhabit.filmsearchapp.data.network.services

import com.coolhabit.filmsearchapp.data.network.FilmsApi
import com.coolhabit.filmsearchapp.data.network.mappers.toDomain
import com.coolhabit.filmsearchapp.domain.api.IMoviesApiService
import com.coolhabit.filmsearchapp.domain.entities.Movie

class FilmsApiService(private val api: FilmsApi) : IMoviesApiService {
    override suspend fun searchMovies(query: String?, page: Int?): List<Movie> {
        val result = mutableListOf<Movie>()
        val pageCount = page ?: 1
        for (i in 1..pageCount) {
            result.addAll(api.getMovies(query, i).Search.map { it.toDomain() })
        }
        return result
    }

    override suspend fun getMovieById(movieId: String?): Movie {
        return api.getMovieDetails(movieId).toDomain()
    }
}
