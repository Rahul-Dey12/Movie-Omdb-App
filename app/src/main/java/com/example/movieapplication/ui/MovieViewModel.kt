package com.example.movieapplication.ui

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.movieapplication.API_KEY
import com.example.movieapplication.data.MovieApi
import com.example.movieapplication.data.MoviePagingSource
import com.example.movieapplication.data.dto.Movie
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieViewModel @Inject constructor(
    private val movieApi: MovieApi,
) : ViewModel() {

    private val _moviePagingData = MutableStateFlow<PagingData<Movie>>(PagingData.empty())
    val moviePagingData = _moviePagingData.asStateFlow()

    val searchText = mutableStateOf("")

    var selectedMovie: Movie? = null


    fun search(searchString: String) {
        searchText.value = searchString

        viewModelScope.launch(Dispatchers.IO) {
            getPagingData(searchString)
                .cachedIn(viewModelScope)
                .collectLatest {
                    _moviePagingData.value = it
                }
        }
    }

    fun setMovieSelected(movie: Movie) {
        selectedMovie = movie
    }

    private fun getPagingData(query: String) =
        Pager(
            config = PagingConfig(pageSize = 10),
            pagingSourceFactory = {
                MoviePagingSource(query, movieApi)
            }
        ).flow
}