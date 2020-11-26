package com.movie.app.ui.pleerscreen

import android.content.Context
import android.content.Intent
import com.movie.app.data.remote.model.MovieRemoteModel
import ru.terrakok.cicerone.android.support.SupportAppScreen

const val URL_KEY = "URL_KEY"

class PlayerScreen(private val movieModel: MovieRemoteModel) : SupportAppScreen() {

    override fun getActivityIntent(context: Context): Intent? =
        Intent(context, PlayerActivity::class.java).putExtra(URL_KEY, movieModel.video)
}