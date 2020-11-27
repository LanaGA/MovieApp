package com.movie.app.ui.infoscreen

import androidx.fragment.app.Fragment
import com.movie.app.data.remote.model.MovieRemoteModel
import ru.terrakok.cicerone.android.support.SupportAppScreen

class InfoScreen(private val movieModel: MovieRemoteModel) : SupportAppScreen() {
    override fun getFragment(): Fragment? {
        return InfoFragment.newInstance(movieModel)
    }
}