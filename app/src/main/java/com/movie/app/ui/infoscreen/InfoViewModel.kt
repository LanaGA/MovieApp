package com.movie.app.ui.infoscreen

import androidx.lifecycle.viewModelScope
import com.movie.app.base.BaseViewModel
import com.movie.app.base.Event
import com.movie.app.data.MovieRepository
import com.movie.app.ui.mainscreen.DataEvent
import com.movie.app.ui.mainscreen.STATUS
import com.movie.app.ui.mainscreen.UiEvent
import com.movie.app.ui.mainscreen.ViewState
import com.movie.app.ui.pleerscreen.PlayerScreen
import kotlinx.coroutines.launch
import ru.terrakok.cicerone.Router
import java.io.IOException

class InfoViewModel(private val router: Router) : BaseViewModel<ViewState>() {
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
            is DataEvent.OnError -> {
                return ViewState(status = STATUS.ERROR, movieList = listOf(), movie = null)
            }
        }
        return null
    }

}