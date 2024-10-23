package com.example.movieapplication.data

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.movieapplication.API_KEY
import com.example.movieapplication.data.dto.Movie

class MoviePagingSource(
    private val query: String,
    private val apiService: MovieApi
) : PagingSource<Int, Movie>() {
    override fun getRefreshKey(state: PagingState<Int, Movie>): Int? {
        return state.anchorPosition?.let {
            state.closestPageToPosition(it)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(it)?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Movie> {
        try {
            val pageNumber = params.key ?: 1
            val apiResponse = apiService.getMovies(API_KEY, query, pageNumber)
            val movies = apiResponse.body()?.movie
            if(movies!=null){
                return LoadResult.Page(
                    data = movies,
                    prevKey = if(pageNumber==1) null else pageNumber - 1,
                    nextKey = pageNumber+1
                )
            }else{
                return LoadResult.Error(
                    Exception("API Error")
                )
            }

        } catch (ex: Exception){
            return LoadResult.Error(ex)
        }
    }
}