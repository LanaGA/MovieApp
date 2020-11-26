package com.movie.app.ui.infoscreen

import com.movie.app.base.Event
import com.movie.app.data.remote.model.MovieRemoteModel

data class ViewState(
    val status: STATUS
)

sealed class UiEvent : Event {
    data class OnOpenMoviePlayer(val movieModel: MovieRemoteModel) : UiEvent()
    data class OnLoadInformation(val movieModel: MovieRemoteModel) : UiEvent()
}

sealed class DataEvent : Event {
    data class SuccessLoadInformation(val movieModel: MovieRemoteModel) : DataEvent()
}

sealed class STATUS {
    object LOAD : STATUS()
    data class CONTENT(val movieModel: MovieRemoteModel) : STATUS()
    object ERROR: STATUS()
}