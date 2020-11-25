package com.movie.app.data.remote

import com.movie.app.base.Either
import com.movie.app.base.attempt
import com.movie.app.data.MovieRepository
import com.movie.app.data.remote.model.MovieRemoteModel

class MovieInteractor(
    private val movieRepository: MovieRepository
){
    suspend fun getMoviesInfo(): Either<Throwable, List<MovieRemoteModel>> =
        attempt { movieRepository.getMoviesInfo() }
}