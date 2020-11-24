package com.movie.app.data

import com.movie.app.base.Either
import com.movie.app.base.attempt
import com.movie.app.ui.mainscreen.model.MovieModel

interface MovieRepository {
   suspend fun getMoviesInfo(): List<MovieModel>
}