package com.movie.app.ui.infoscreen

import com.movie.app.base.utils.BaseViewModel
import com.movie.app.base.Event
import com.movie.app.data.MovieRepository
import com.movie.app.ui.mainscreen.DataEvent
import com.movie.app.ui.mainscreen.STATUS
import com.movie.app.ui.mainscreen.UiEvent
import com.movie.app.ui.mainscreen.ViewState
import com.movie.app.ui.pleerscreen.PlayerScreen
import ru.terrakok.cicerone.Router

class InfoViewModel(private val moviesRepository: MovieRepository, private val router: Router) : BaseViewModel<ViewState>() {
    override fun initialViewState(): ViewState = ViewState(
        status = STATUS.LOAD,
        movieList = listOf(),
        movie = null
    )

    override fun reduce(event: Event, previousState: ViewState): ViewState? {
        when (event) {
            is UiEvent.OpenMoviePlayer -> {
                router.navigateTo(PlayerScreen(event.movieModel))
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