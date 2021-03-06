package com.movie.app.data.remote

import com.movie.app.data.remote.MovieApi

class MovieRemoteSource (private val movieApi: MovieApi) {
    suspend fun getMoviesResponse() = movieApi.getMoviesInfo()
}