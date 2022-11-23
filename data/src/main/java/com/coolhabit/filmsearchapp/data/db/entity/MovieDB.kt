package com.coolhabit.filmsearchapp.data.db.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.coolhabit.filmsearchapp.data.db.AppDatabase.Companion.DATABASE_NAME
import com.coolhabit.filmsearchapp.data.db.dao.MovieDao
import com.coolhabit.filmsearchapp.domain.entities.Movie

@Entity(tableName = MovieDao.TABLE_NAME)
data class MovieDB(
    @PrimaryKey
    @ColumnInfo(name = "id") val id: String,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "poster") val poster: String,
    @ColumnInfo(name = "year") val year: String,
    @ColumnInfo(name = "type") val type: String,
)

fun Movie.toData() = MovieDB(
    id = this.imdbId,
    name = this.name,
    poster = this.poster,
    year = this.year,
    type = this.type,
)

fun MovieDB.toDomain() = Movie(
    imdbId = this.id,
    name = this.name,
    poster = this.poster,
    year = this.year,
    type = this.type,
    isFavorite = true,
    commentsList = emptyList(),
)
