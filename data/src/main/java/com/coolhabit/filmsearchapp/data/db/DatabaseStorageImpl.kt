package com.coolhabit.filmsearchapp.data.db

import android.content.Context
import androidx.room.Room
import com.coolhabit.filmsearchapp.data.db.entity.toData
import com.coolhabit.filmsearchapp.data.db.entity.toDomain
import com.coolhabit.filmsearchapp.domain.api.IDatabaseStorage
import com.coolhabit.filmsearchapp.domain.entities.Movie
import com.coolhabit.filmsearchapp.domain.entities.MovieComment

class DatabaseStorageImpl(context: Context) : IDatabaseStorage {

    private val database: AppDatabase =
        Room
            .databaseBuilder(context, AppDatabase::class.java, AppDatabase.DATABASE_NAME)
            .fallbackToDestructiveMigration().build()

    override suspend fun addMovieToFavorite(movie: Movie) {
        database.moviesDao().insert(movie.toData())
    }

    override suspend fun removeMovieFromFavorite(movie: Movie) {
        database.moviesDao().delete(movie.toData())
    }

    override suspend fun getFavoriteMovies(): List<Movie> {
        return database.moviesDao().getFavoriteMovies().map {
            it.toDomain()
        }
    }

    override suspend fun addMovieWithComments(movieComment: MovieComment) {
        database.commentsDao().insert(movieComment.toData())
    }

    override suspend fun getCommentsList(): List<MovieComment> {
        return database.commentsDao().getMoviesWithComments().map {
            it.toDomain()
        }
    }
}
