package com.movie.app.ui.infoscreen.model

import com.movie.app.base.Item
import com.movie.app.ui.mainscreen.model.GenreModel

data class InfoMovieModel(
    val title: String,
    val original_title: String,
    val genres: List<GenreModel>,
    val overview: String,
    val poster_path: String,
    val release_date: String,
    val vote_average: Double
) : Item