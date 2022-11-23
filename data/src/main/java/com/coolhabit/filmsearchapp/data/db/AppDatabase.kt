package com.coolhabit.filmsearchapp.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.coolhabit.filmsearchapp.data.db.AppDatabase.Companion.DATABASE_VERSION
import com.coolhabit.filmsearchapp.data.db.converters.StringArrayConverter
import com.coolhabit.filmsearchapp.data.db.dao.MovieDao
import com.coolhabit.filmsearchapp.data.db.dao.MoviesWithCommentsDao
import com.coolhabit.filmsearchapp.data.db.entity.MovieDB
import com.coolhabit.filmsearchapp.data.db.entity.MovieWithCommentsDB

@Database(
    entities = [
        MovieDB::class,
        MovieWithCommentsDB::class,
    ], version = DATABASE_VERSION, exportSchema = false
)
@TypeConverters(StringArrayConverter::class)
abstract class AppDatabase : RoomDatabase() {

    abstract fun moviesDao(): MovieDao
    abstract fun commentsDao(): MoviesWithCommentsDao

    companion object {

        const val DATABASE_NAME = "movie_db"
        const val DATABASE_VERSION = 2
    }
}
