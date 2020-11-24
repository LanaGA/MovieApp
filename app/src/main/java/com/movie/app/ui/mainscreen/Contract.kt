package com.movie.app.ui.mainscreen

import com.movie.app.base.Event
import com.movie.app.ui.mainscreen.model.MovieModel

data class ViewState(
    val status: STATUS,
    val movieList: List<MovieModel>,
    val movie: MovieModel?
)

sealed class UiEvent : Event {
    object LoadMovies : UiEvent()
    data class OpenMovieInfo(val movieModel: MovieModel) : UiEvent()
    data class OpenMoviePlayer(val movieModel: MovieModel) : UiEvent()
}

sealed class DataEvent : Event {
    data class OnSuccessAllMovieRequest(val movieList: List<MovieModel>) : DataEvent()
    data class OnSuccessMovieRequest(val movie: MovieModel) : DataEvent()

    object OnError : DataEvent()
}

enum class STATUS {
    LOAD,
    CONTENT,
    ERROR
}