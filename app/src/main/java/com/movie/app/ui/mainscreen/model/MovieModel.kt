package com.movie.app.ui.mainscreen.model

import com.movie.app.base.Item

data class MovieModel(
    val title: String,
    val poster_path: String,
    val release_date: String,
    val vote_average: Double
): Item