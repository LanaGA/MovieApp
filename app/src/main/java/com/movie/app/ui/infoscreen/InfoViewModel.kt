package com.movie.app.ui.infoscreen

import com.movie.app.base.BaseViewModel
import com.movie.app.base.Event
import com.movie.app.data.MovieRepository
import com.movie.app.ui.mainscreen.ViewState

class InfoViewModel (private val moviesRepository: MovieRepository) : BaseViewModel<ViewState>() {
    override fun initialViewState(): ViewState {
        TODO("Not yet implemented")
    }

    override fun reduce(event: Event, previousState: ViewState): ViewState? {
        TODO("Not yet implemented")
    }

}