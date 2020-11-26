package com.movie.app.ui.mainscreen


import androidx.lifecycle.viewModelScope
import com.movie.app.base.BaseViewModel
import com.movie.app.base.Event
import com.movie.app.data.remote.MovieInteractor
import com.movie.app.ui.infoscreen.InfoScreen
import kotlinx.coroutines.launch
import ru.terrakok.cicerone.Router

class MovieViewModel(private val movieInteractor: MovieInteractor, private val router: Router) :
    BaseViewModel<ViewState>() {

    override fun initialViewState(): ViewState = ViewState(
        status = STATUS.LOAD
    )

    override fun reduce(event: Event, previousState: ViewState): ViewState? {
        when (event) {
            is UiEvent.LoadMovies -> {
                viewModelScope.launch {
                    movieInteractor.getMoviesInfo().fold(
                        onError = { processDataEvent(DataEvent.OnError) },
                        onSuccess = { processDataEvent(DataEvent.OnSuccessAllMovieRequest(it)) }
                    )

                }
                return ViewState(status = STATUS.LOAD)
            }
            is UiEvent.OpenMovieInfo -> {
                viewModelScope.launch {
                    router.navigateTo(InfoScreen(event.movieModel))
                }
            }
            is DataEvent.OnSuccessAllMovieRequest -> {
                return ViewState(STATUS.CONTENT(event.movieList))
            }
            is DataEvent.OnError -> {
                return ViewState(status = STATUS.ERROR)
            }
        }
        return null
    }
}