package com.example.paging.ui.gallery

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.*
import com.example.paging.repoistory.TmdbRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MovieViewModel @Inject constructor(
    repository: TmdbRepository
) : ViewModel() {
    val movies = repository.getMovies().cachedIn(viewModelScope)
}