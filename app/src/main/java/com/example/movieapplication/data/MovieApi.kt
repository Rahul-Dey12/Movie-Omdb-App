package com.example.movieapplication.data

import com.example.movieapplication.data.dto.MovieApiResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieApi {

    @GET("?")
    suspend fun getMovies(
        @Query("apikey") apiKey: String,
        @Query("s") searchString: String,
        @Query("page") page: Int
    ): Response<MovieApiResponse>
}