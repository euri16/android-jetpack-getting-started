package com.jetpackinitialexample.app.ui.movielist

import com.jetpackinitialexample.app.data.network.responses.Movie
import com.jetpackinitialexample.app.ui.common.BaseRecyclerViewAdapter
import com.outcomehealth.jetpackinitialexample.R

/**
 * Created by euryperez on 2019-10-18.
 */
class MovieListAdapter : BaseRecyclerViewAdapter<Movie>() {
    override fun getLayoutRes() = R.layout.itv_movie
}