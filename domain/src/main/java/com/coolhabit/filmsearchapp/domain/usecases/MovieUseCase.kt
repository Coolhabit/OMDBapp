package com.coolhabit.filmsearchapp.domain.usecases

import com.coolhabit.filmsearchapp.domain.api.IDatabaseStorage
import com.coolhabit.filmsearchapp.domain.api.IMoviesApiService
import com.coolhabit.filmsearchapp.domain.entities.Movie

class MovieUseCase constructor(
    private val api: IMoviesApiService,
    private val database: IDatabaseStorage,
) {

    suspend fun loadMoviesList(query: String?, pages: Int?): List<Movie>? {
        val favList = database.getFavoriteMovies()
        val currentList = api.searchMovies(query, pages)
        currentList.map {
            it.isFavorite = favList.any { fav -> fav.imdbId == it.imdbId }
        }
        return currentList
    }

    suspend fun getMovieById(id: String?): Movie {
        val favList = database.getFavoriteMovies()
        val currentMovie = api.getMovieById(id)
        currentMovie.isFavorite = favList.any { it.imdbId == id}
        return currentMovie
    }

    suspend fun addMovieToFav(movie: Movie) {
        database.addMovieToFavorite(movie)
    }

    suspend fun removeMovieFromFav(movie: Movie) {
        database.removeMovieFromFavorite(movie)
    }
}
