package com.movie.app.ui.mainscreen

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.hannesdorfmann.adapterdelegates4.ListDelegationAdapter
import com.movie.app.R
import com.movie.app.extension.setAdapterAndCleanupOnDetachFromWindow
import com.movie.app.extension.setData
import com.movie.app.ui.infoscreen.openMovieAdapterDelegate
import kotlinx.android.synthetic.main.fragment_main.*
import kotlinx.android.synthetic.main.item_error.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class MovieFragment : Fragment(R.layout.fragment_main) {
    private val viewModel: MovieViewModel by viewModel()
    private val adapter = ListDelegationAdapter(openMovieAdapterDelegate {
        viewModel.processUiEvent(UiEvent.OpenMovieInfo(it))
    })

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.viewState.observe(viewLifecycleOwner, Observer(::render))
        initView()
    }

    private fun initView() {
        rvMoviesList.layoutManager = GridLayoutManager(requireContext(), 2)
        rvMoviesList.setAdapterAndCleanupOnDetachFromWindow(adapter)
        swipeRefreshLayout.setOnRefreshListener {
            viewModel.processUiEvent(UiEvent.LoadMovies)
        }
        viewModel.processUiEvent(UiEvent.LoadMovies)
        errorReload.setOnClickListener {
            viewModel.processUiEvent(UiEvent.LoadMovies)
        }
    }

    private fun render(viewState: ViewState) {
        when (viewState.status) {
            is STATUS.LOAD -> {
                rvMoviesVisibility(true)
                errorPageVisibility(false)
                swipeRefreshLayout.isRefreshing = true
            }
            is STATUS.CONTENT -> {
                adapter.setData(viewState.status.movieList)
                swipeRefreshLayout.isRefreshing = false
            }
            is STATUS.ERROR -> {
                rvMoviesVisibility(false)
                errorPageVisibility(true)
                swipeRefreshLayout.isRefreshing = false
            }
        }
    }

    private fun errorPageVisibility(isVisible: Boolean) {
        if(isVisible)
            errorPage.visibility = View.VISIBLE
        else
            errorPage.visibility = View.GONE
    }

    private fun rvMoviesVisibility(isVisible: Boolean) {
        if(isVisible)
            rvMoviesList.visibility = View.VISIBLE
        else
            rvMoviesList.visibility = View.GONE
    }
}