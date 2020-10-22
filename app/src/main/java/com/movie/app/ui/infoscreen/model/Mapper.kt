package com.movie.app.ui.infoscreen.model

import com.movie.app.data.remote.model.GenreRemoteModel
import com.movie.app.data.remote.model.MovieRemoteModel
import com.movie.app.ui.mainscreen.model.GenreModel

fun MovieRemoteModel.mapToUiModel(): InfoMovieModel {
    return InfoMovieModel(
        title = title,
        original_title = original_title,
        poster_path = poster_path,
        genres = genres.map { it.mapToUiModel() },
        overview = overview,
        release_date = release_date,
        vote_average = vote_average
    )
}

fun GenreRemoteModel.mapToUiModel(): GenreModel {
    return GenreModel(name)
}