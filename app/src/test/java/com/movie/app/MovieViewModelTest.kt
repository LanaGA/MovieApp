package com.movie.app

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.movie.app.base.toLeftEither
import com.movie.app.base.toRightEither
import com.movie.app.data.remote.MovieInteractor
import com.movie.app.data.remote.model.MovieRemoteModel
import com.movie.app.ui.mainscreen.*
import com.nhaarman.mockitokotlin2.*
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Rule
import org.junit.Test
import ru.terrakok.cicerone.Router

class MovieViewModelTest {

    @Rule
    @JvmField
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @Rule
    @JvmField
    val coroutineRule = CoroutineRule()
    private lateinit var viewModel: MovieViewModel
    private val viewStateObserver: Observer<ViewState> = mock()
    private val interactor: MovieInteractor = mock()
    private val router: Router = mock()

    @Test
    fun `test load status success`() {
        val m = movieRemoteModel
        mockMovie(m)
        createViewModel()
        val vs = captureViewState()
        Assert.assertEquals(vs.status, STATUS.LOAD)
    }

    @Test
    fun `test request movies success`() {
        val m = movieRemoteModel
        mockMovie(m)
        createViewModel()
        viewModel.processDataEvent(DataEvent.OnSuccessAllMovieRequest(listOf(m)))
        val vs = captureViewState()
        Assert.assertEquals(vs.movieList.first(), m)
    }

    @Test
    fun `test request movies failure`() {
        createViewModel()
        mockMovieRequestError()
    }

    private fun createViewModel() {
        viewModel = MovieViewModel(interactor, router)
        viewModel.viewState.observeForever(viewStateObserver)
    }

    private fun mockMovie(m: MovieRemoteModel) =
        runBlocking {
            whenever(interactor.getMoviesInfo())
                .thenReturn(listOf(m).toRightEither())
        }

    private fun mockMovieRequestError(throwable: Throwable = RuntimeException()) =
        runBlocking {
            whenever(interactor.getMoviesInfo())
                .thenReturn(throwable.toLeftEither())
        }

    private fun captureViewState(): ViewState = capture {
        verify(viewStateObserver, atLeastOnce()).onChanged(it.capture())
    }

}
