package com.jetpackinitialexample.app.ui.movielist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.jetpackinitialexample.app.data.network.responses.Movie
import com.jetpackinitialexample.app.ui.common.BaseFragment
import com.jetpackinitialexample.app.ui.common.SimpleAdapter
import com.jetpackinitialexample.app.utils.setup
import com.outcomehealth.jetpackinitialexample.R
import com.outcomehealth.jetpackinitialexample.databinding.MovieListFragmentBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

/**
 * Created by euryperez on 2019-10-18.
 */
class MovieListFragment : BaseFragment() {

    override val _viewModel: MovieListViewModel by viewModel()
    private var binding: MovieListFragmentBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        if (binding == null) {
            binding =
                DataBindingUtil.inflate(inflater, R.layout.movie_list_fragment, container, false)

            binding?.lifecycleOwner = this

            val adapter = SimpleAdapter<Movie>(R.layout.itv_movie) {
                //TODO: Open details
            }

            binding?.rvVideos?.setup(adapter)

            binding?.viewModel = _viewModel
        }
        return binding?.root
    }

    fun foo() {
        val adapter = SimpleAdapter<String>(R.layout.itv_movie) {

        }
    }
}