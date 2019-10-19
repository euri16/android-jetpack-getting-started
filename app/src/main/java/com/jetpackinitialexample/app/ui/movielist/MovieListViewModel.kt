package com.jetpackinitialexample.app.ui.movielist

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.jetpackinitialexample.app.data.network.responses.APIResult
import com.jetpackinitialexample.app.data.network.responses.Movie
import com.jetpackinitialexample.app.repositories.MoviesRepository
import com.jetpackinitialexample.app.ui.common.BaseViewModel
import com.jetpackinitialexample.app.ui.common.NavigationCommand
import kotlinx.coroutines.launch

/**
 * Created by euryperez on 2019-10-18.
 */
class MovieListViewModel(app: Application, private val repository: MoviesRepository) :
    BaseViewModel(app) {

    val _movies = MutableLiveData<List<Movie>>()
    val movies: LiveData<List<Movie>> = _movies

    init {
        viewModelScope.launch {
            when (val result = repository.getTopRatedMovies()) {
                is APIResult.Success -> {
                    _movies.value = result.data.results
                }
                is APIResult.Error -> {
                    //TODO: Implement
                }
            }
        }
    }

    fun openMovieDetail(movieId: String) {
        navigationCommand.postValue(
            NavigationCommand.To(
                MovieListFragmentDirections.toMovieDetail(movieId)
            )
        )
    }
}