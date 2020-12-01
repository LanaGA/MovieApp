package com.movie.app.data.remote.model

import com.google.gson.annotations.SerializedName

data class MovieResponseModel(
    @SerializedName("page")
    val page: Int,
    @SerializedName("results")
    val movies: List<MovieRemoteModel>,
    @SerializedName("total_pages")
    val totalPages: Int,
    @SerializedName("total_results")
    val totalResults: Int
)