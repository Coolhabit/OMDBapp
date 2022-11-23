package com.coolhabit.filmsearchapp.data.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.coolhabit.filmsearchapp.data.db.entity.MovieWithCommentsDB

@Dao
interface MoviesWithCommentsDao {
    companion object {
        const val TABLE_NAME = "movies_with_comments"
    }

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(movie: MovieWithCommentsDB)

    @Query("SELECT * FROM $TABLE_NAME")
    suspend fun getMoviesWithComments(): List<MovieWithCommentsDB>
}
