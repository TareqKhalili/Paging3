package com.example.paging.repoistory

import androidx.paging.*
import com.example.paging.data.TmdbPagingSource
import com.example.paging.data.TmdbApi
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TmdbRepository @Inject constructor(
    private val api: TmdbApi
) {
    fun getMovies() =
        Pager(
            config = PagingConfig(
                pageSize = 20,
                maxSize = 100,
                enablePlaceholders = false
            ),
            pagingSourceFactory = { TmdbPagingSource(api) }
        ).flow
}