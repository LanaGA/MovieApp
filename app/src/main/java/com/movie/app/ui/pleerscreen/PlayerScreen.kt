package com.movie.app.ui.pleerscreen

import androidx.fragment.app.Fragment
import com.movie.app.ui.infoscreen.InfoFragment
import com.movie.app.ui.mainscreen.model.MovieModel
import ru.terrakok.cicerone.android.support.SupportAppScreen

class PlayerScreen(private val movieModel: MovieModel?) : SupportAppScreen() {
    override fun getFragment(): Fragment? {
        return PlayerFragment(movieModel)
    }
}