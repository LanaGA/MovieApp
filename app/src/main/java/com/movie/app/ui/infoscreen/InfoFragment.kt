package com.movie.app.ui.infoscreen

import android.os.Bundle
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.movie.app.R
import com.movie.app.di.MOVIES_QUALIFIER
import com.movie.app.ui.mainscreen.model.MovieModel
import kotlinx.android.synthetic.main.fragment_detail_movie.*
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.qualifier.named
import ru.terrakok.cicerone.Router

class InfoFragment(private val movie: MovieModel?): Fragment(R.layout.fragment_detail_movie) {
    private val viewModel: InfoViewModel by viewModel()
    private val router: Router by inject(named(MOVIES_QUALIFIER))

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        attachModel()
        displayOverview()
        overview.setOnClickListener {
            displayOverview()
        }
        details.setOnClickListener {
            displayDetails()
        }
    }

    private fun attachModel(){
        Glide.with(requireContext())
            .load(movie?.poster_path)
            .into(posterImageView)
        titleToolbar.title = movie?.title
        yearTextView.text = movie?.release_date
        genreTextView.text = movie?.genres.toString()
        scoreTextView.text = movie?.vote_average.toString()
        descriptionTextView.text = movie?.overview
        scoreTextView.text = movie?.vote_average.toString()

    }
    private fun displayOverview(){
        genreTextView.visibility = GONE
        scoreTextView.visibility = GONE
    }

    private fun displayDetails(){
        genreTextView.visibility = VISIBLE
        scoreTextView.visibility = VISIBLE
    }
}