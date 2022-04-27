package com.example.paging.data

import androidx.paging.PagingSource
import androidx.paging.PagingState
import retrofit2.HttpException
import java.io.IOException

private const val STARTING_PAGE_INDEX = 1

class TmdbPagingSource(
    private val tmdbApi: TmdbApi,
) : PagingSource<Int, TmdbMovie>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, TmdbMovie> {
        val position = params.key ?: STARTING_PAGE_INDEX

        return try {
            val response = tmdbApi.getMovies(position)
            val movies = response.results

            LoadResult.Page(
                data = movies,
                prevKey = if (position == STARTING_PAGE_INDEX) null else position - 1,
                nextKey = if (movies.isEmpty()) null else position + 1
            )
        } catch (exception: IOException) {
            LoadResult.Error(exception)
        } catch (exception: HttpException) {
            LoadResult.Error(exception)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, TmdbMovie>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }
}