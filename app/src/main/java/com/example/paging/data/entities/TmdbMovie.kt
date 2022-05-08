package com.example.paging.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.paging.other.Constants.MOVIES_TABLE_NAME

@Entity(tableName = MOVIES_TABLE_NAME)
data class TmdbMovie(
    @PrimaryKey
    val id: String,
    val overview: String,
    val title: String,
    val backdrop_path: String?,
    val poster_path: String?,
    val vote_average: String,
    val page: Int = 0
) {
    val backdropUrl: String
        get() = "https://image.tmdb.org/t/p/original/$poster_path"
}