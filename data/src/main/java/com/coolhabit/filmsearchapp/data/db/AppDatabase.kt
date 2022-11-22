package com.coolhabit.filmsearchapp.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.coolhabit.filmsearchapp.data.db.AppDatabase.Companion.DATABASE_VERSION
import com.coolhabit.filmsearchapp.data.db.dao.MovieDao
import com.coolhabit.filmsearchapp.data.db.entity.MovieDB

@Database(entities = [MovieDB::class], version = DATABASE_VERSION, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    abstract fun channelsDao(): MovieDao

    companion object {
        const val DATABASE_NAME = "movie_db"
        const val DATABASE_VERSION = 1
    }
}
