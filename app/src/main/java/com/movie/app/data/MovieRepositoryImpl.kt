package com.movie.app.data

import com.movie.app.data.remote.model.MovieRemoteModel
import com.movie.app.data.remote.MovieRemoteSource

class MovieRepositoryImpl(private val remoteSource: MovieRemoteSource) : MovieRepository {
    override suspend fun getMoviesInfo(): List<MovieRemoteModel> =
        remoteSource.getMoviesResponse().movies

}