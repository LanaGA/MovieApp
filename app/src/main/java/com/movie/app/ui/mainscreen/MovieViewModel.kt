package com.movie.app.ui.mainscreen


import androidx.lifecycle.viewModelScope
import com.movie.app.base.BaseViewModel
import com.movie.app.base.Event
import com.movie.app.data.MovieRepository
import com.movie.app.ui.infoscreen.InfoScreen
import kotlinx.coroutines.launch
import ru.terrakok.cicerone.Router
import java.io.IOException

class MovieViewModel(private val moviesRepository: MovieRepository, private val router: Router) : BaseViewModel<ViewState>() {

    override fun initialViewState(): ViewState = ViewState(
        status = STATUS.LOAD,
        movieList = listOf()
    )

    override fun reduce(event: Event, previousState: ViewState): ViewState? {
        when (event) {
            is UiEvent.LoadMovies -> {
                viewModelScope.launch {
                    try {
                        val moviesList = moviesRepository.getMoviesInfo()
                        processDataEvent(DataEvent.OnSuccessAllMovieRequest(moviesList))
                    } catch (e: IOException) {
                        processDataEvent(DataEvent.OnError(e))
                    }
                }
                return ViewState(status = STATUS.LOAD, movieList = listOf())
            }
            is UiEvent.OpenMovieInfo -> {
                router.navigateTo(InfoScreen(event.movieModel))
            }
            is DataEvent.OnSuccessAllMovieRequest -> {
                return ViewState(STATUS.CONTENT, event.movieList)
            }
            is DataEvent.OnError -> {
                return ViewState(status = STATUS.ERROR, movieList = listOf())
            }
        }
        return null
    }
}