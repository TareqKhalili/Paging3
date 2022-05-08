package com.example.paging.ui.movies

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.*
import com.example.paging.data.TmdbRemoteMediator
import com.example.paging.data.local.TmdbLocalDatabase
import com.example.paging.data.remote.TmdbApi
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MovieViewModel @Inject constructor(
    database: TmdbLocalDatabase,
    retrofit: TmdbApi,
) : ViewModel() {
    @OptIn(ExperimentalPagingApi::class)
    val movies = Pager(
        config = PagingConfig(pageSize = 1),
        remoteMediator = TmdbRemoteMediator(database, retrofit)
    ) {
        database.moviesDao.getAll()
    }.flow.cachedIn(viewModelScope)
}