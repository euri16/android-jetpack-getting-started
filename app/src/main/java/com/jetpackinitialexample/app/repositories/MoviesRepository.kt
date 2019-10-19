package com.jetpackinitialexample.app.repositories

import com.jetpackinitialexample.app.data.network.MoviesService
import com.jetpackinitialexample.app.data.network.responses.APIResult
import com.jetpackinitialexample.app.data.network.responses.MoviesResponse

/**
 * Created by euryperez on 2019-10-18.
 */
class MoviesRepository(val api: MoviesService) : BaseRepository(){

    suspend fun getTopRatedMovies(): APIResult<MoviesResponse> =
        getAPIResult(safeApiCall { api.getTopRatedMovies() })
}