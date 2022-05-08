package com.example.paging.data

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import com.example.paging.data.entities.TmdbMovie
import com.example.paging.data.local.TmdbLocalDatabase
import com.example.paging.data.remote.TmdbApi
import java.lang.Exception

@OptIn(ExperimentalPagingApi::class)
class TmdbRemoteMediator(
    private val database: TmdbLocalDatabase,
    private val retrofit: TmdbApi
) : RemoteMediator<Int, TmdbMovie>() {
    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, TmdbMovie>
    ): MediatorResult {
        return try {
            val loadKey = when (loadType) {
                LoadType.REFRESH -> 1

                LoadType.PREPEND -> return MediatorResult.Success(endOfPaginationReached = true)

                LoadType.APPEND -> {
                    val lastElement = state.lastItemOrNull()
                        ?: return MediatorResult.Success(endOfPaginationReached = false)

                    lastElement.page + 1
                }
            }

            val response = retrofit.getMovies(loadKey)

            if (loadType == LoadType.REFRESH) {
                database.moviesDao.clearAll()
            }

            database.moviesDao.insertAll(response.results.map {
                it.copy(page = loadKey)
            })

            MediatorResult.Success(endOfPaginationReached = response.results.isEmpty())
        } catch (exception: Exception) {
            MediatorResult.Error(exception)
        }
    }
}