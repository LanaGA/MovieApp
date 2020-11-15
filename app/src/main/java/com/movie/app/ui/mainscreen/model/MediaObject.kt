package com.movie.app.ui.mainscreen.model

data class MediaObject(
    private var title: String?,
    private var media_url: String?,
    private var thumbnail: String?
) {
    fun getTitle(): String? {
        return title
    }

    fun getMedia_url(): String? {
        return media_url
    }

    fun getThumbnail(): String? {
        return thumbnail
    }
}