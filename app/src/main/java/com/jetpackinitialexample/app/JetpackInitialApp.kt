package com.jetpackinitialexample.app

import android.app.Application
import com.jetpackinitialexample.app.data.network.API
import com.jetpackinitialexample.app.repositories.MoviesRepository
import com.jetpackinitialexample.app.ui.movielist.MovieListAdapter
import com.jetpackinitialexample.app.ui.movielist.MovieListViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.startKoin
import org.koin.dsl.module

/**
 * Created by euryperez on 2019-10-18.
 */
class JetpackInitialApp : Application() {

    override fun onCreate() {
        super.onCreate()

        val myModule = module {
            viewModel { MovieListViewModel(get(), get()) }
            single { MoviesRepository(get()) }
            single { API.moviesAPI }
            factory { MovieListAdapter() }
        }

        startKoin {
            androidContext(this@JetpackInitialApp)
            modules(listOf(myModule))
        }
    }
}