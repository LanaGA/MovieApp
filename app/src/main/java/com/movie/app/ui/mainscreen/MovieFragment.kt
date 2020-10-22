package com.movie.app.ui.mainscreen

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.hannesdorfmann.adapterdelegates4.ListDelegationAdapter
import com.movie.app.R
import com.movie.app.base.Item
import kotlinx.android.synthetic.main.fragment_main.*
import org.koin.android.ext.android.get
import org.koin.androidx.viewmodel.ext.android.viewModel

class MovieFragment : Fragment(R.layout.fragment_main) {
    private val viewModel: MovieViewModel by viewModel()
    private val adapter: ListDelegationAdapter<List<Item>> = get()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initView()
        viewModel.viewState.observe(viewLifecycleOwner, Observer(::render))
        viewModel.processUiEvent(UiEvent.LoadMovies)
    }

    private fun initView() {
        rvMoviesList.layoutManager = GridLayoutManager(requireContext(), 2)
        rvMoviesList.adapter = adapter
    }

    private fun render(viewState: ViewState) {
        when (viewState.status) {
            STATUS.LOAD -> {
            }
            STATUS.CONTENT -> {
                adapter.items = viewState.movieList
                adapter.notifyDataSetChanged()
            }
            STATUS.ERROR -> {
            }
        }
    }
}