package com.movie.app

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.movie.app.base.toLeftEither
import com.movie.app.base.toRightEither
import com.movie.app.data.MovieRepository
import com.movie.app.data.remote.MovieInteractor
import com.movie.app.data.remote.model.GenreRemoteModel
import com.movie.app.data.remote.model.MovieRemoteModel
import com.movie.app.ui.mainscreen.DataEvent
import com.movie.app.ui.mainscreen.MovieViewModel
import com.movie.app.ui.mainscreen.STATUS
import com.movie.app.ui.mainscreen.ViewState
import com.movie.app.ui.mainscreen.model.MovieModel
import com.movie.app.ui.mainscreen.model.mapToUiModel
import com.nhaarman.mockitokotlin2.*
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Rule
import org.junit.Test
import ru.terrakok.cicerone.Router
import java.text.SimpleDateFormat

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
    private val format = SimpleDateFormat("yyyy-MM-dd")

    @Test
    fun `test load status success`() {
        val m = MovieRemoteModel(
            isAdult = false,
            genres = listOf(GenreRemoteModel(name = "Drama")),
            id = 244786,
            original_language = "en",
            original_title = "Whiplash",
            overview = "Under the direction of a ruthless instructor, a talented young drummer begins to pursue perfection at any cost, even his humanity.",
            release_date = format.parse("2014-10-10"),
            poster_path = "https://upload.wikimedia.org/wikipedia/en/0/01/Whiplash_poster.jpg",
            popularity = 8.441533,
            title = "Whiplash",
            video = "http://techslides.com/demos/sample-videos/small.mp4, vote_average=8.5, vote_count=856",
            vote_average = 8.5,
            vote_count = 856
        ).mapToUiModel()
        mockMovie(m)
        createViewModel()
        val vs = captureViewState()
        Assert.assertEquals(vs.status, STATUS.LOAD)
    }

    @Test
    fun `test request movies success`() {
        val m = MovieRemoteModel(
            isAdult = false,
            genres = listOf(GenreRemoteModel(name = "Drama")),
            id = 244786,
            original_language = "en",
            original_title = "Whiplash",
            overview = "Under the direction of a ruthless instructor, a talented young drummer begins to pursue perfection at any cost, even his humanity.",
            release_date = format.parse("2014-10-10"),
            poster_path = "https://upload.wikimedia.org/wikipedia/en/0/01/Whiplash_poster.jpg",
            popularity = 8.441533,
            title = "Whiplash",
            video = "http://techslides.com/demos/sample-videos/small.mp4, vote_average=8.5, vote_count=856",
            vote_average = 8.5,
            vote_count = 856
        ).mapToUiModel()
        mockMovie(m)
        createViewModel()
        viewModel.processDataEvent(DataEvent.OnSuccessAllMovieRequest(listOf(m)))
        val vs = captureViewState()
        Assert.assertEquals(vs.movieList.first(), m)
    }

    @Test
    fun `test request movies failure`(throwable: Throwable = RuntimeException()) {
        createViewModel()
        runBlocking {
            whenever(interactor.getMoviesInfo())
                .thenReturn(throwable.toLeftEither())
        }
        val viewState = captureViewState()
    }

    private fun createViewModel() {
        viewModel = MovieViewModel(interactor, router)
        viewModel.viewState.observeForever(viewStateObserver)
    }

    private fun mockMovie(m: MovieModel) =
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

inline fun <reified T : Any> capture(invokeCaptor: (KArgumentCaptor<T>) -> Unit): T {
    val captor = argumentCaptor<T>()
    invokeCaptor(captor)
    return captor.lastValue
}