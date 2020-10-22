package com.movie.app.data.remote.model

import com.movie.app.ui.mainscreen.model.mapToUiModel
import com.movie.app.data.remote.MovieApi
import com.movie.app.ui.mainscreen.model.MovieModel

class MovieRemoteSource(private val movieApi: MovieApi) {

    suspend fun getMoviesInfo(): List<MovieModel> =
        movieApi.getMoviesInfo().movies.map { it.mapToUiModel() }

}