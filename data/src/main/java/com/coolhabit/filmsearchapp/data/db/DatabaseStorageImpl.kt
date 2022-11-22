package com.coolhabit.filmsearchapp.data.db

import android.content.Context
import androidx.room.Room
import com.coolhabit.filmsearchapp.data.db.entity.toData
import com.coolhabit.filmsearchapp.data.db.entity.toDomain
import com.coolhabit.filmsearchapp.domain.api.IDatabaseStorage
import com.coolhabit.filmsearchapp.domain.entities.Movie

class DatabaseStorageImpl(context: Context) : IDatabaseStorage {

    private val database: AppDatabase =
        Room
            .databaseBuilder(context, AppDatabase::class.java, AppDatabase.DATABASE_NAME)
            .fallbackToDestructiveMigration().build()

    override suspend fun addMovieToFavorite(movie: Movie) {
        database.channelsDao().insert(movie.toData())
    }

    override suspend fun removeMovieFromFavorite(movie: Movie) {
        database.channelsDao().delete(movie.toData())
    }

    override suspend fun getFavoriteMovies(): List<Movie> {
        return database.channelsDao().getFavoriteMovies().map {
            it.toDomain()
        }
    }
}
