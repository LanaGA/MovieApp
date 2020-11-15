package com.movie.app.ui.mainscreen

import android.view.View
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.RequestManager
import com.movie.app.R
import com.movie.app.ui.mainscreen.model.MediaObject
import kotlinx.android.synthetic.main.item_movie.view.*

class VideoPlayerViewHolder(var parent: View) : RecyclerView.ViewHolder(
    parent
) {
    var title: TextView = parent.title
    var thumbnail: ImageView = parent.thumbnail
    var volumeControl: ImageView = parent.volume_control
    var progressBar: ProgressBar = parent.progressBar
    var requestManager: RequestManager? = null
    fun onBind(mediaObject: MediaObject, requestManager: RequestManager?) {
        this.requestManager = requestManager
        parent.tag = this
        title.text = mediaObject.getTitle()
        this.requestManager
            ?.load(mediaObject.getThumbnail())
            ?.into(thumbnail)
    }

}