package com.movie.app.data.remote.model

import com.google.gson.annotations.SerializedName


data class GenreRemoteModel(
    @SerializedName("name")
    val name: String
)