package com.example.kinoteatr.ui.mainscreen

import com.example.kinoteatr.base.Event
import com.example.kinoteatr.ui.mainscreen.model.MovieModel

data class ViewState(
    val status: STATUS,
    val movieList: List<MovieModel>
)

sealed class UiEvent : Event {
    object LoadMovies : UiEvent()
}

sealed class DataEvent : Event {
    data class RequestMovie(val movieList: List<MovieModel>) : DataEvent()
    data class OnError(val error: Throwable) : DataEvent()
}

enum class STATUS {
    LOAD,
    CONTENT,
    ERROR
}