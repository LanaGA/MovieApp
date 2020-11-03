package com.movie.app.ui.mainscreen.model

import com.movie.app.data.remote.model.GenreRemoteModel
import com.movie.app.data.remote.model.MovieRemoteModel


fun MovieRemoteModel.mapToUiModel(): MovieModel {
    return MovieModel(
        title = title,
        original_title = original_title,
        original_language = original_language,
        genres = genres.map { it.mapToUiModel() },
        overview = overview,
        poster_path = poster_path,
        release_date = release_date,
        vote_average = vote_average,
        vote_count = vote_count,
        popularity = popularity,
        video = video
    )
}

fun GenreRemoteModel.mapToUiModel(): GenreModel{
    return GenreModel(name = name)
}