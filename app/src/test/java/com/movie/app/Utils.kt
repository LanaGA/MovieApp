package com.movie.app

import com.movie.app.data.remote.model.GenreRemoteModel
import com.movie.app.data.remote.model.MovieRemoteModel
import com.nhaarman.mockitokotlin2.KArgumentCaptor
import com.nhaarman.mockitokotlin2.argumentCaptor
import java.text.SimpleDateFormat

inline fun <reified T : Any> capture(invokeCaptor: (KArgumentCaptor<T>) -> Unit): T {
    val captor = argumentCaptor<T>()
    invokeCaptor(captor)
    return captor.lastValue
}

private val format = SimpleDateFormat("yyyy-MM-dd")
val movieRemoteModel = MovieRemoteModel(
    isAdult = false,
    genres = listOf(GenreRemoteModel(name = "Drama")),
    id = 244786,
    original_language = "en",
    original_title = "Whiplash",
    overview = "Under the direction of a ruthless instructor, a talented young drummer begins to pursue perfection at any cost, even his humanity.",
    release_date = format.parse("2014-10-10"),
    poster_path = "https://upload.wikimedia.org/wikipedia/en/0/01/Whiplash_poster.jpg",
    popularity = 8.441533,
    title = "Whiplash",
    video = "http://techslides.com/demos/sample-videos/small.mp4, vote_average=8.5, vote_count=856",
    vote_average = 8.5,
    vote_count = 856
)