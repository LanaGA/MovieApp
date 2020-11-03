package com.movie.app.ui.mainscreen.model

import com.movie.app.base.Item
import java.util.*

data class MovieModel(
    val title: String,
    val original_title: String,
    val original_language: String,
    val genres: List<GenreModel>,
    val overview: String,
    val poster_path: String,
    val release_date: Date,
    val vote_average: Double,
    val vote_count: Int,
    val popularity: Double,
    val video: String
): Item