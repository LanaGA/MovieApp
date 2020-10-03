package com.movie.app.data

import com.movie.app.data.remote.MovieApi
import com.movie.app.ui.mainscreen.model.MovieModel

class MovieRepositoryImpl(private val moviesApi: MovieApi) : MovieRepository {
    override suspend fun getMoviesInfo(): List<MovieModel> {
        return moviesApi.getMoviesInfo().movies.map {
            it.mapToUiModel()
        }
    }
}