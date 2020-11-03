package com.movie.app.ui.infoscreen

import androidx.fragment.app.Fragment
import com.movie.app.ui.mainscreen.model.MovieModel
import ru.terrakok.cicerone.android.support.SupportAppScreen

class InfoScreen(private val movieModel: MovieModel?) : SupportAppScreen() {
    override fun getFragment(): Fragment? {
        return InfoFragment(movieModel)
    }
}