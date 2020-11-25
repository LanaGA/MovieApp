package com.movie.app.data

import com.movie.app.data.remote.MovieApi
import com.movie.app.data.remote.model.MovieRemoteModel
import com.movie.app.data.remote.model.MovieRemoteSource

class MovieRepositoryImpl(private val remoteSource: MovieRemoteSource) : MovieRepository {
    override suspend fun getMoviesInfo(): List<MovieRemoteModel> =
        remoteSource.getMoviesResponse().movies

}