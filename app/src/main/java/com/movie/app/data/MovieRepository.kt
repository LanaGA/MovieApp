package com.movie.app.data

import com.movie.app.data.remote.model.MovieRemoteModel

interface MovieRepository {
   suspend fun getMoviesInfo(): List<MovieRemoteModel>
}