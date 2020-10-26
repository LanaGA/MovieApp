package com.movie.app.data.remote.model

import com.google.gson.annotations.SerializedName
import java.util.*

data class MovieRemoteModel(
    @SerializedName("adult")
    val isAdult: Boolean,
    @SerializedName("genres")
    val genres: List<GenreRemoteModel>,
    @SerializedName("id")
    val id: Int,
    @SerializedName("original_language")
    val original_language: String,
    @SerializedName("original_title")
    val original_title: String,
    @SerializedName("overview")
    val overview: String,
    @SerializedName("release_date")
    val release_date: Date,
    @SerializedName("poster_path")
    val poster_path: String,
    @SerializedName("popularity")
    val popularity: Double,
    @SerializedName("title")
    val title: String,
    @SerializedName("video")
    val video: String,
    @SerializedName("vote_average")
    val vote_average: Double,
    @SerializedName("vote_count")
    val vote_count: Int
)



data class MovieListRemoteModel(
    @SerializedName("results")
    val results: List<MovieRemoteModel>
)