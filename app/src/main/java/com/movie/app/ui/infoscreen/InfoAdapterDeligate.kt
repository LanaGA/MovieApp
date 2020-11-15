package com.movie.app.ui.infoscreen

import android.widget.TextView
import com.bumptech.glide.Glide
import com.hannesdorfmann.adapterdelegates4.AdapterDelegate
import com.hannesdorfmann.adapterdelegates4.dsl.adapterDelegateLayoutContainer
import com.movie.app.R
import com.movie.app.base.Item
import com.movie.app.base.utils.formatYear
import com.movie.app.ui.mainscreen.model.MovieModel

fun openMovieAdapterDelegate(onClick: (MovieModel) -> Unit): AdapterDelegate<List<Item>> =
    adapterDelegateLayoutContainer<MovieModel, Item>(
        R.layout.item_movie
    ) {
        containerView.setOnClickListener { onClick(item) }

        bind {

        }
    }