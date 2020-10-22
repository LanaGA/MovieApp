package com.movie.app.di

import android.util.Log
import com.hannesdorfmann.adapterdelegates4.ListDelegationAdapter
import com.movie.app.base.BASE_URL
import com.movie.app.data.MovieRepository
import com.movie.app.data.MovieRepositoryImpl
import com.movie.app.data.remote.MovieApi
import com.movie.app.data.remote.model.MovieRemoteSource
import com.movie.app.ui.infoscreen.InfoViewModel
import com.movie.app.ui.mainscreen.MovieViewModel
import com.movie.app.ui.mainscreen.movieAdapterDelegate
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import ru.terrakok.cicerone.Cicerone
import ru.terrakok.cicerone.NavigatorHolder
import ru.terrakok.cicerone.Router

const val MOVIES_QUALIFIER = "MOVIES_QUALIFIER"
val appModule = module {

    single<Retrofit> {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    single<OkHttpClient> {
        OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor(
                object : HttpLoggingInterceptor.Logger {
                    override fun log(message: String) {
                        Log.d("OkHttp", message)
                    }
                }
            ).apply {
                setLevel(HttpLoggingInterceptor.Level.BODY)
            })
            .build()
    }

    single<MovieApi>{
        get<Retrofit>().create(MovieApi::class.java)
    }

    single<MovieRemoteSource> {
        MovieRemoteSource(get())
    }

    single<MovieRepository> {
        MovieRepositoryImpl(get())
    }


}

val navModule = module {

    single<Cicerone<Router>>(named(MOVIES_QUALIFIER)) {
        Cicerone.create()
    }

    single<NavigatorHolder>(named(MOVIES_QUALIFIER)) {
        get<Cicerone<Router>>(named(MOVIES_QUALIFIER)).navigatorHolder
    }

    single<Router>(named(MOVIES_QUALIFIER)) {
        get<Cicerone<Router>>(named(MOVIES_QUALIFIER)).router
    }
}

val viewModelModule = module {
    viewModel<MovieViewModel> {
        MovieViewModel(get(), get(named(MOVIES_QUALIFIER)))
    }
    viewModel<InfoViewModel> {
        InfoViewModel(get())
    }
}
