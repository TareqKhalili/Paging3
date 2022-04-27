package com.example.paging.data


data class TmdbMovie(
    val id: String,
    val overview: String,
    val title: String,
    val backdrop_path: String,
    val poster_path: String,
    val vote_average: String,
) {
    val backdropUrl: String
        get() = "https://image.tmdb.org/t/p/original/$poster_path"
}