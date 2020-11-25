package com.movie.app.ui.mainscreen

import com.movie.app.base.Event
import com.movie.app.data.remote.model.MovieRemoteModel

data class ViewState(
    val status: STATUS,
    val movieList: List<MovieRemoteModel>,
    val movie: MovieRemoteModel?
)

sealed class UiEvent : Event {
    object LoadMovies : UiEvent()
    data class OpenMovieInfo(val movieModel: MovieRemoteModel) : UiEvent()
    data class OpenMoviePlayer(val movieModel: MovieRemoteModel) : UiEvent()
}

sealed class DataEvent : Event {
    data class OnSuccessAllMovieRequest(val movieList: List<MovieRemoteModel>) : DataEvent()

    object OnError : DataEvent()
}

enum class STATUS {
    LOAD,
    CONTENT,
    ERROR
}