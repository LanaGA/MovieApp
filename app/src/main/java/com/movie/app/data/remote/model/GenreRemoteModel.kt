package com.movie.app.data.remote.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class GenreRemoteModel(
    @SerializedName("name")
    val name: String
): Parcelable