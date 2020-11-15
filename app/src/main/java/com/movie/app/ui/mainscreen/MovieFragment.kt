package com.movie.app.ui.mainscreen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestManager
import com.bumptech.glide.request.RequestOptions
import com.hannesdorfmann.adapterdelegates4.ListDelegationAdapter
import com.movie.app.R
import com.movie.app.base.utils.VerticalSpacingItemDecorator
import com.movie.app.base.utils.setAdapterAndCleanupOnDetachFromWindow
import com.movie.app.base.utils.setData
import com.movie.app.ui.infoscreen.openMovieAdapterDelegate
import com.movie.app.ui.mainscreen.model.MediaObject
import kotlinx.android.synthetic.main.fragment_main.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.util.ArrayList

class MovieFragment : Fragment(R.layout.fragment_main) {

    private val viewModel: MovieViewModel by viewModel()
    private val adapter = ListDelegationAdapter(openMovieAdapterDelegate {
        viewModel.processUiEvent(UiEvent.OpenMovieInfo(it))
    })

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.viewState.observe(viewLifecycleOwner, Observer(::render))
        initRecyclerView()
    }

    private fun initRecyclerView() {
        rvMoviesList.layoutManager = LinearLayoutManager(requireContext())
        rvMoviesList.addItemDecoration(VerticalSpacingItemDecorator(10))

        rvMoviesList.setAdapterAndCleanupOnDetachFromWindow(adapter)
        viewModel.processUiEvent(UiEvent.LoadMovies)

        val mediaObjects: ArrayList<MediaObject> = ArrayList<MediaObject>(listOf(null))
        rvMoviesList.setMediaObjects(mediaObjects)

        val adapter = VideoPlayerRecyclerAdapter(mediaObjects, initGlide())
        rvMoviesList.adapter = adapter
    }

    private fun initGlide(): RequestManager {
        val options: RequestOptions = RequestOptions()
            .placeholder(R.drawable.white_background)
            .error(R.drawable.white_background)
        return Glide.with(this)
            .setDefaultRequestOptions(options)
    }

    override fun onDestroy() {
        rvMoviesList.releasePlayer()
        super.onDestroy()
    }

    private fun initView() {
        rvMoviesList.layoutManager = GridLayoutManager(requireContext(), 2)
        rvMoviesList.setAdapterAndCleanupOnDetachFromWindow(adapter)
        viewModel.processUiEvent(UiEvent.LoadMovies)
    }

    private fun render(viewState: ViewState) {
        when (viewState.status) {
            STATUS.LOAD -> {
            }
            STATUS.CONTENT -> {
                adapter.setData(viewState.movieList)
            }
            STATUS.ERROR -> {
            }
        }
    }
}