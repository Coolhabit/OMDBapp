package com.coolhabit.filmsearchapp.data.db.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.coolhabit.filmsearchapp.data.db.AppDatabase.Companion.DATABASE_NAME
import com.coolhabit.filmsearchapp.data.db.entity.MovieDB

@Dao
interface MovieDao {
    companion object {
        const val TABLE_NAME = "movies"
    }

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(movie: MovieDB)

    @Delete
    suspend fun delete(movie: MovieDB)

    @Query("SELECT * FROM $TABLE_NAME")
    suspend fun getFavoriteMovies(): List<MovieDB>
}
