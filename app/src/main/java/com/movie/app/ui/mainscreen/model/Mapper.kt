package com.movie.app.ui.mainscreen.model

import com.movie.app.data.remote.model.GenreRemoteModel
import com.movie.app.data.remote.model.MovieRemoteModel


fun MovieRemoteModel.mapToUiModel(): MovieModel {
    return MovieModel(
        title = title,
        poster_path = poster_path,
        release_date = release_date,
        vote_average = vote_average
    )
}
