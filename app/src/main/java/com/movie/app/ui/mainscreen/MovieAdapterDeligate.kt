package com.example.kinoteatr.ui.mainscreen

import android.widget.TextView
import com.bumptech.glide.Glide
import com.example.kinoteatr.R
import com.example.kinoteatr.base.Item
import com.example.kinoteatr.ui.mainscreen.model.MovieModel
import com.hannesdorfmann.adapterdelegates4.AdapterDelegate
import com.hannesdorfmann.adapterdelegates4.dsl.adapterDelegateLayoutContainer


fun movieAdapterDelegate(): AdapterDelegate<List<Item>> =
    adapterDelegateLayoutContainer<MovieModel, Item>(
        R.layout.item_movie
    ) {
        bind {
            findViewById<TextView>(R.id.titleTextView).text = item.title
            findViewById<TextView>(R.id.yearTextView).text = item.release_date
            Glide.with(containerView)
                .load(item.poster_path)
                .into(findViewById(R.id.posterImageView))
        }
    }