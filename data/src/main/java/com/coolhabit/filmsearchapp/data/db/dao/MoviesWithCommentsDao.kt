package com.coolhabit.filmsearchapp.data.db.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.coolhabit.filmsearchapp.data.db.entity.MovieDB
import com.coolhabit.filmsearchapp.data.db.entity.MovieWithCommentsDB
import com.coolhabit.filmsearchapp.domain.entities.MovieComment

@Dao
interface MoviesWithCommentsDao {
    companion object {
        const val TABLE_NAME = "movies_with_comments"
    }

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(movie: MovieWithCommentsDB)

    @Query("UPDATE $TABLE_NAME SET comments=:newList WHERE id=:filmId")
    suspend fun updateCommentsList(filmId: String, newList: List<String>)

    @Query("SELECT * FROM ${MoviesWithCommentsDao.TABLE_NAME}")
    suspend fun getMoviesWithComments(): List<MovieWithCommentsDB>
}
