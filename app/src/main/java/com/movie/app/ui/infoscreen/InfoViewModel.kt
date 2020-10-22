package com.movie.app.ui.infoscreen

import androidx.lifecycle.viewModelScope
import com.movie.app.base.BaseViewModel
import com.movie.app.base.Event
import com.movie.app.data.MovieRepository
import com.movie.app.ui.mainscreen.DataEvent
import com.movie.app.ui.mainscreen.STATUS
import com.movie.app.ui.mainscreen.UiEvent
import com.movie.app.ui.mainscreen.ViewState
import kotlinx.coroutines.launch
import java.io.IOException

class InfoViewModel (private val moviesRepository: MovieRepository) : BaseViewModel<ViewState>() {
    override fun initialViewState(): ViewState = ViewState(
        status = STATUS.LOAD,
        movieList = listOf(),
        movie = null
    )

    override fun reduce(event: Event, previousState: ViewState): ViewState? {
        when (event) {
            is UiEvent.OpenMovieInfo -> {
                viewModelScope.launch {
                    try {
                        processDataEvent(DataEvent.OnSuccessMovieRequest(event.movieModel))
                    } catch (e: IOException) {
                        processDataEvent(DataEvent.OnError(e))
                    }
                }
            }
            is DataEvent.OnSuccessMovieRequest -> {
                return ViewState(STATUS.CONTENT, listOf(), event.movie)
            }
            is DataEvent.OnError -> {
                return ViewState(status = STATUS.ERROR, movieList = listOf(), movie = null)
            }
        }
        return null
    }

}