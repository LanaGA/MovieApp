package com.movie.app.ui.infoscreen

import com.movie.app.base.BaseViewModel
import com.movie.app.base.Event
import com.movie.app.ui.pleerscreen.PlayerScreen
import ru.terrakok.cicerone.Router

class InfoViewModel(private val router: Router) : BaseViewModel<ViewState>() {
    override fun initialViewState(): ViewState = ViewState(
        status = STATUS.LOAD
    )

    override fun reduce(event: Event, previousState: ViewState): ViewState? {
        when (event) {
            is UiEvent.OnOpenMoviePlayer -> {
                router.navigateTo(PlayerScreen(event.movieModel))
            }
            is UiEvent.OnLoadInformation -> {
                DataEvent.SuccessLoadInformation(event.movieModel)
            }
            is DataEvent.SuccessLoadInformation -> {
                return ViewState(status = STATUS.CONTENT(event.movieModel))
            }
        }
        return null
    }

}