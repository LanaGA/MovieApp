package com.movie.app.ui.mainscreen

import android.widget.TextView
import com.bumptech.glide.Glide
import com.hannesdorfmann.adapterdelegates4.AdapterDelegate
import com.hannesdorfmann.adapterdelegates4.dsl.adapterDelegateLayoutContainer
import com.movie.app.R
import com.movie.app.base.Item
import com.movie.app.data.remote.model.MovieRemoteModel

fun openMovieAdapterDelegate(onClick: (MovieRemoteModel) -> Unit): AdapterDelegate<List<Item>> =
    adapterDelegateLayoutContainer<MovieRemoteModel, Item>(
        R.layout.item_movie
    ) {
        containerView.setOnClickListener { onClick(item) }

        bind {
            findViewById<TextView>(R.id.titleText).text = item.title
            Glide.with(containerView)
                .load(item.poster_path)
                .into(findViewById(R.id.posterImage))

        }
    }