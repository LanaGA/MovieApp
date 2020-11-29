package com.movie.app.ui.infoscreen

import android.content.res.Resources
import android.os.Bundle
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import com.google.android.material.appbar.AppBarLayout
import com.google.android.material.chip.Chip
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayout.OnTabSelectedListener
import com.movie.app.R
import com.movie.app.data.remote.model.MovieRemoteModel
import com.movie.app.di.MOVIES_QUALIFIER
import com.movie.app.extension.formatGenres
import com.movie.app.extension.formatYear
import kotlinx.android.synthetic.main.fragment_detail_movie.*
import kotlinx.android.synthetic.main.item_description.*
import kotlinx.android.synthetic.main.item_more_details.*
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.qualifier.named
import ru.terrakok.cicerone.Router
import kotlin.math.abs

class InfoFragment : Fragment(R.layout.fragment_detail_movie) {
    private val viewModel: InfoViewModel by viewModel()
    private val router: Router by inject(named(MOVIES_QUALIFIER))
    private val movie by lazy {
        requireNotNull(
            requireArguments().getParcelable<MovieRemoteModel>(
                KEY_MOVIE
            )
        )
    }

    companion object {
        fun newInstance(movie: MovieRemoteModel) =
            InfoFragment()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initUi(movie)
        viewModel.viewState.observe(viewLifecycleOwner, Observer(::render))
    }

    private fun initUi(movie: MovieRemoteModel) {
        attachModel(movie)

        tabLayout.addOnTabSelectedListener(object : OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                showPosition(tabLayout.selectedTabPosition)
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {
                showPosition(tabLayout.selectedTabPosition)
            }
        })
        tabLayout.selectTab(tabLayout.getTabAt(0))

        buttonBack.setOnClickListener {
            router.exit()
        }

        appbar.addOnOffsetChangedListener(AppBarLayout.OnOffsetChangedListener { appBarLayout, verticalOffset ->
            val percent = (abs(verticalOffset).toFloat() / appBarLayout.totalScrollRange)
            playButtonUp.alpha = percent
            playButtonDown.alpha = 1.0f - percent
        })

        playButtonUp.setOnClickListener {
            if (playButtonUp.alpha == 1.0f) {
                viewModel.processUiEvent(UiEvent.OnOpenMoviePlayer(movie))
            }
        }

        playButtonDown.setOnClickListener {
            if (playButtonDown.alpha == 1.0f) {
                viewModel.processUiEvent(UiEvent.OnOpenMoviePlayer(movie))
            }
        }
    }

    private fun render(viewState: ViewState) {
        when (viewState.status) {
            is STATUS.LOAD -> {
                viewModel.processUiEvent(UiEvent.OnLoadInformation(movie))
            }
            is STATUS.CONTENT -> {
            }
            is STATUS.ERROR -> {
            }
        }
    }

    private fun showPosition(position: Int) {
        when (position) {
            0 -> changeTabsContentVisibility(false)
            1 -> changeTabsContentVisibility(true)
        }
    }

    private fun changeTabsContentVisibility(isVisible: Boolean) {
        more_details.visibility = if (isVisible) VISIBLE else GONE
    }

    private fun attachModel(movie: MovieRemoteModel) {
        val res: Resources = resources
        Glide.with(requireContext())
            .load(movie.poster_path)
            .into(posterImageView)
        titleToolbar.title = movie.title
        descriptionTextView.text = movie.overview

        originTitleTextView.text = res.getString(R.string.originTitle, movie.original_title)
        originLanguageTextView.text =
            res.getString(R.string.originLanguage, movie.original_language)
        yearTextView.text = res.getString(R.string.releaseYear, formatYear(movie.release_date))
        formatGenres(movie.genres).forEach {
            Chip(genreChip.context).apply {
                text = it
                genreChip.addView(this)
                setChipBackgroundColorResource(R.color.colorPrimary)
                isClickable = false
            }
        }
        scoreTextView.text = res.getString(R.string.score, movie.vote_average.toString())
        ratingBar.rating = movie.vote_average.toFloat()
        voteTotalTextView.text = res.getString(R.string.total_vote, movie.vote_count.toString())

    }
}