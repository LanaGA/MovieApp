package com.movie.app.data

import com.movie.app.data.remote.model.GenreRemoteModel
import com.movie.app.data.remote.model.MovieRemoteModel
import com.movie.app.ui.mainscreen.model.GenreModel
import com.movie.app.ui.mainscreen.model.MovieModel


fun MovieRemoteModel.mapToUiModel(): MovieModel {
    return MovieModel(
        title = title,
        original_title = original_title,
        genres = genres.map { it.mapToUiModel() },
        poster_path = poster_path,
        overview = overview,
        release_date = release_date,
        vote_average = vote_average
    )
}

fun GenreRemoteModel.mapToUiModel(): GenreModel {
    return GenreModel(name)
}