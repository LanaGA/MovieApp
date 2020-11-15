package com.movie.app.base.utils

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.hannesdorfmann.adapterdelegates4.AbsDelegationAdapter
import com.movie.app.ui.mainscreen.model.GenreModel
import java.util.*
import kotlin.collections.ArrayList

fun <T> AbsDelegationAdapter<T>.setData(data: T) {
    items = data
    notifyDataSetChanged()
}

fun RecyclerView.setAdapterAndCleanupOnDetachFromWindow(mAdapter: RecyclerView.Adapter<*>) {
    adapter = mAdapter
    addOnAttachStateChangeListener(object : View.OnAttachStateChangeListener {
        override fun onViewAttachedToWindow(v: View?) {
        }

        override fun onViewDetachedFromWindow(v: View?) {
            adapter = null
            removeOnAttachStateChangeListener(this)
        }
    })
}

fun Double.round(decimals: Int): Double = "%.${decimals}f".format(this).toDouble()

fun formatYear(date: Date): String {
    val calendar = Calendar.getInstance().apply { time = date }
    return calendar.get(Calendar.YEAR).toString()
}

fun formatVoteCount(vote: Int): String {
    return "(Total vote: $vote)"
}

fun formatGenres(genres: List<GenreModel>): List<String> {
    val string = ArrayList<String>()
    genres.forEachIndexed { _, genreModel ->
        string.add(genreModel.name)
    }
    return string
}