package com.example.kinoteatr.ui.mainscreen

import androidx.lifecycle.viewModelScope
import com.example.kinoteatr.base.BaseViewModel
import com.example.kinoteatr.base.Event
import com.example.kinoteatr.data.MovieRepository
import com.example.kinoteatr.ui.mainscreen.model.MovieModel
import kotlinx.coroutines.launch
import java.io.IOException

class MovieViewModel(private val moviesRepository: MovieRepository) : BaseViewModel<ViewState>() {

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
                        processDataEvent(DataEvent.RequestMovie(moviesList))
                    } catch (e: IOException) {
                        processDataEvent(DataEvent.OnError(e))
                    }
                }
                return ViewState(status = STATUS.LOAD, movieList = listOf())
            }
            is DataEvent.RequestMovie -> {
                return ViewState(STATUS.CONTENT, event.movieList)
            }
            is DataEvent.OnError -> {
                return ViewState(status = STATUS.ERROR, movieList = listOf())
            }
        }
        return null
    }
}