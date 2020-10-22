package com.movie.app.ui.mainscreen

import androidx.fragment.app.Fragment
import ru.terrakok.cicerone.android.support.SupportAppScreen

class MoviesScreen : SupportAppScreen() {
    override fun getFragment(): Fragment? {
        return MovieFragment()
    }
}