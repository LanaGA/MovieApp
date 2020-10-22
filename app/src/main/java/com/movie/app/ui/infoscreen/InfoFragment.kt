package com.movie.app.ui.infoscreen

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.movie.app.R
import com.movie.app.di.MOVIES_QUALIFIER
import com.movie.app.ui.mainscreen.model.MovieModel
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.qualifier.named
import ru.terrakok.cicerone.Router

class InfoFragment(private val movieModel: MovieModel?): Fragment(R.layout.fragment_detail_movie) {
    private val viewModel: InfoViewModel by viewModel()
    private val router: Router by inject(named(MOVIES_QUALIFIER))

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }
}