package com.coolhabit.filmsearchapp.data.db.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.coolhabit.filmsearchapp.data.db.dao.MoviesWithCommentsDao
import com.coolhabit.filmsearchapp.domain.entities.MovieComment

@Entity(tableName = MoviesWithCommentsDao.TABLE_NAME)
data class MovieWithCommentsDB(
    @PrimaryKey
    @ColumnInfo(name = "id") val id: String,
    @ColumnInfo(name = "comments") val comments: List<String>
)

fun MovieWithCommentsDB.toDomain(): MovieComment {
    return MovieComment(
        movieId = this.id,
        commentsList = this.comments,
    )
}

fun MovieComment.toData(): MovieWithCommentsDB {
    return MovieWithCommentsDB(
        id = this.movieId,
        comments = this.commentsList,
    )
}
