package com.movie.app.ui.pleerscreen

import android.content.Context
import android.content.Intent
import com.movie.app.ui.mainscreen.model.MovieModel
import ru.terrakok.cicerone.android.support.SupportAppScreen

const val URL_KEY = "URL_KEY"
const val TITLE_KEY = "TITLE_KEY"
class PlayerScreen(private val movieModel: MovieModel?) : SupportAppScreen() {

    override fun getActivityIntent(context: Context): Intent? {
        movieModel!!
        val intent = Intent(context, PlayerActivity::class.java)
        intent.putExtra(URL_KEY, movieModel.video)
        intent.putExtra(TITLE_KEY, movieModel.title)
        return intent
    }
}