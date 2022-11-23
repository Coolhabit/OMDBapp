package com.coolhabit.filmsearchapp.domain.usecases

import com.coolhabit.filmsearchapp.domain.api.IDatabaseStorage
import com.coolhabit.filmsearchapp.domain.api.IMoviesApiService
import com.coolhabit.filmsearchapp.domain.entities.Movie
import com.coolhabit.filmsearchapp.domain.entities.MovieComment
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope

class MovieUseCase constructor(
    private val api: IMoviesApiService,
    private val database: IDatabaseStorage,
) {
    suspend fun loadMoviesList(query: String?, pages: Int?): List<Movie>? {
        return coroutineScope {
            val favAsync = async {
                database.getFavoriteMovies()
            }
            val commentsAsync = async {
                database.getCommentsList()
            }
            val moviesAsync = async {
                api.searchMovies(query, pages)
            }

            val list = moviesAsync.await()
            list.map { movie ->
                movie.isFavorite = favAsync.await().any { fav -> fav.imdbId == movie.imdbId }
                movie.commentsList =
                    commentsAsync.await().find { it.movieId == movie.imdbId }?.commentsList ?: emptyList()
            }
            list
        }
    }

    suspend fun getMovieById(id: String?): Movie {
        return coroutineScope {
            val favAsync = async {
                database.getFavoriteMovies()
            }
            val commentsAsync = async {
                database.getCommentsList()
            }
            val movieAsync = async {
                api.getMovieById(id)
            }

            val currentMovie = movieAsync.await()
            currentMovie.isFavorite = favAsync.await().any { it.imdbId == id}
            currentMovie.commentsList = commentsAsync.await().find { it.movieId == id }?.commentsList ?: emptyList()
            currentMovie
        }
    }

    suspend fun addMovieToFav(movie: Movie) {
        database.addMovieToFavorite(movie)
    }

    suspend fun removeMovieFromFav(movie: Movie) {
        database.removeMovieFromFavorite(movie)
    }

    suspend fun addComment(movieId: String, newList: List<String>) {
        database.addMovieWithComments(MovieComment(movieId, newList))
    }
}
