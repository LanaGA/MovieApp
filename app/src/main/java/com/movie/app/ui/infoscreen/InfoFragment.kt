package com.movie.app.ui.infoscreen

import android.os.Bundle
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.google.android.material.chip.Chip
import com.google.android.material.tabs.TabLayout
import com.movie.app.R
import com.movie.app.base.utils.formatGenres
import com.movie.app.base.utils.formatVoteCount
import com.movie.app.base.utils.formatYear
import com.movie.app.base.utils.round
import com.movie.app.di.MOVIES_QUALIFIER
import com.movie.app.ui.mainscreen.UiEvent
import com.movie.app.ui.mainscreen.model.MovieModel
import kotlinx.android.synthetic.main.fragment_detail_movie.*
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.qualifier.named
import ru.terrakok.cicerone.Router


class InfoFragment(private val movie: MovieModel?) : Fragment(R.layout.fragment_detail_movie) {
    private val viewModel: InfoViewModel by viewModel()
    private val router: Router by inject(named(MOVIES_QUALIFIER))


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        attachModel()

        tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener{
            override fun onTabSelected(tab: TabLayout.Tab?) {
                when(tabLayout.selectedTabPosition){
                    0 -> display(VISIBLE)
                    1 -> display(GONE)
                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {
            }

        })

        playButton.setOnClickListener {
            if (movie != null) {
                viewModel.processUiEvent(UiEvent.OpenMoviePlayer(movie))
            } else {
                Toast.makeText(
                    requireContext(),
                    "Invalid movie: no link for player",
                    Toast.LENGTH_LONG
                ).show()
            }
        }

    }

    private fun attachModel() {
        //TODO: добавить обработку ошибок(отсутствие интернета)
        movie!!
        Glide.with(requireContext())
            .load(movie.poster_path)
            .into(posterImageView)
        titleToolbar.title = movie.title
        descriptionTextView.text = movie.overview

        originTitleTextView.text = movie.original_title
        originLanguageTextView.text = movie.original_language
        yearTextView.text = movie.release_date.let { formatYear(it) }
        formatGenres(movie.genres).forEach {
            Chip(genreChip.context).apply {
                text = it
                genreChip.addView(this)
                isClickable = false
            }
        }
        scoreTextView.text = movie.vote_average.round(1).toString()
        voteTotalTextView.text = movie.vote_count.let { formatVoteCount(it) }

    }

    private fun display(value: Int) {
        originTitleTextView.visibility = value
        namedOriginTitle.visibility = value

        originLanguageTextView.visibility = value
        namedOriginLanguage.visibility = value

        yearTextView.visibility = value
        namedYearTextView.visibility = value

        genreChip.visibility = value
        namedGenreTextView.visibility = value

        scoreTextView.visibility = value
        namedScoreTextView.visibility = value
        voteTotalTextView.visibility = value
    }


}