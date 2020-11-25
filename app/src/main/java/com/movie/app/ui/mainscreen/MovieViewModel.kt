package com.movie.app.ui.mainscreen


import androidx.lifecycle.viewModelScope
import com.movie.app.base.BaseViewModel
import com.movie.app.base.Event
import com.movie.app.data.MovieRepository
import com.movie.app.data.remote.MovieInteractor
import com.movie.app.ui.infoscreen.InfoScreen
import kotlinx.coroutines.launch
import ru.terrakok.cicerone.Router
import java.io.IOException

class MovieViewModel(private val movieInteractor: MovieInteractor, private val router: Router) : BaseViewModel<ViewState>() {

    override fun initialViewState(): ViewState = ViewState(
        status = STATUS.LOAD,
        movieList = listOf(),
        movie = null
    )

    override fun reduce(event: Event, previousState: ViewState): ViewState? {
        when (event) {
            is UiEvent.LoadMovies -> {
                viewModelScope.launch {
                    movieInteractor.getMoviesInfo().fold(
                        {processDataEvent(DataEvent.OnError)},
                        {processDataEvent(DataEvent.OnSuccessAllMovieRequest(it))}
                    )

                }
                return ViewState(status = STATUS.LOAD, movieList = listOf(), movie = null)
            }
            is UiEvent.OpenMovieInfo -> {
                router.navigateTo(InfoScreen(event.movieModel))
            }
            is DataEvent.OnSuccessAllMovieRequest -> {
                return ViewState(STATUS.CONTENT, event.movieList, null)
            }
            is DataEvent.OnError -> {
                return ViewState(status = STATUS.ERROR, movieList = listOf(), movie = null)
            }
        }
        return null
    }
}