package com.example.paging.data

import com.example.paging.BuildConfig
import retrofit2.http.GET
import retrofit2.http.Query

interface TmdbApi {
    companion object {
        const val BASE_URL = "https://api.themoviedb.org/3/"
    }

    @GET("movie/top_rated?api_key=${BuildConfig.TMDB_ACCESS_KEY}")
    suspend fun getMovies(
        @Query("page") page: Int,
    ): TmdbResponse
}


data class TmdbResponse(
    val results: List<TmdbMovie>
)