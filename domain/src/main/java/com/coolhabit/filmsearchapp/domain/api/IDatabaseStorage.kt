package com.coolhabit.filmsearchapp.domain.api

import com.coolhabit.filmsearchapp.domain.entities.Movie
import com.coolhabit.filmsearchapp.domain.entities.MovieComment

interface IDatabaseStorage {

    suspend fun addMovieToFavorite(movie: Movie)

    suspend fun removeMovieFromFavorite(movie: Movie)

    suspend fun getFavoriteMovies(): List<Movie>

    suspend fun addMovieWithComments(movieComment: MovieComment)

    suspend fun getCommentsList(): List<MovieComment>
}
