package com.movie.app.ui.mainscreen

import android.widget.TextView
import com.bumptech.glide.Glide
import com.hannesdorfmann.adapterdelegates4.AdapterDelegate
import com.hannesdorfmann.adapterdelegates4.dsl.adapterDelegateLayoutContainer
import com.movie.app.R
import com.movie.app.base.Item
import com.movie.app.ui.mainscreen.model.MovieModel


fun movieAdapterDelegate(): AdapterDelegate<List<Item>> =
    adapterDelegateLayoutContainer<MovieModel, Item>(
        R.layout.item_movie
    ) {
        bind {
            findViewById<TextView>(R.id.titleText).text = item.title
            findViewById<TextView>(R.id.yearText).text = item.release_date
            Glide.with(containerView)
                .load(item.poster_path)
                .into(findViewById(R.id.posterImage))
        }
    }