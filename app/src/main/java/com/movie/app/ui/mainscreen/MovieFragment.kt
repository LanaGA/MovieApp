package com.movie.app.ui.mainscreen

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.hannesdorfmann.adapterdelegates4.ListDelegationAdapter
import com.movie.app.R
import com.movie.app.base.setAdapterAndCleanupOnDetachFromWindow
import com.movie.app.base.setData
import com.movie.app.ui.infoscreen.openMovieAdapterDelegate
import kotlinx.android.synthetic.main.fragment_main.*
import kotlinx.android.synthetic.main.item_error.*
import kotlinx.android.synthetic.main.item_error.view.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import kotlin.error

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
    }

    private fun render(viewState: ViewState) {
        when (viewState.status) {
            STATUS.LOAD -> {
                rvMoviesList.isVisible = true
                errorPage.isVisible = false
                swipeRefreshLayout.isRefreshing = true
            }
            STATUS.CONTENT -> {
                adapter.setData(viewState.movieList)
                swipeRefreshLayout.isRefreshing = false
            }
            STATUS.ERROR -> {
                rvMoviesList.isVisible = false
                errorPage.isVisible = true
                swipeRefreshLayout.isRefreshing = false
            }
        }
    }
}