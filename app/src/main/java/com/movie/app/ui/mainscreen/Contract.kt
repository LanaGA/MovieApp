package com.movie.app.ui.mainscreen

import com.movie.app.base.Event
import com.movie.app.data.remote.model.MovieRemoteModel

data class ViewState(
    val status: STATUS
)

sealed class UiEvent : Event {
    object LoadMovies : UiEvent()
    data class OpenMovieInfo(val movieModel: MovieRemoteModel) : UiEvent()
}

sealed class DataEvent : Event {
    data class OnSuccessAllMovieRequest(val movieList: List<MovieRemoteModel>) : DataEvent()
    object OnError : DataEvent()
}

sealed class STATUS {
    object LOAD : STATUS()
    data class CONTENT(val movieList: List<MovieRemoteModel>) : STATUS()
    object ERROR : STATUS()
}