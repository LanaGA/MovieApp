package com.movie.app.data

import com.movie.app.ui.mainscreen.model.MovieModel

interface MovieRepository {
   suspend fun getMoviesInfo(): List<MovieModel>
}