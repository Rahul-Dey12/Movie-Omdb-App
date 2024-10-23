package com.example.movieapplication.data.dto

import com.google.gson.annotations.SerializedName

data class MovieApiResponse(
    val Response: String,
    @SerializedName("Search")
    val movie: List<Movie>,
    val totalResults: String
)