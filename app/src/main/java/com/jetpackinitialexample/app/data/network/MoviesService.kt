package com.jetpackinitialexample.app.data.network

import com.jetpackinitialexample.app.data.network.responses.MoviesResponse
import com.outcomehealth.jetpackinitialexample.BuildConfig
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface MoviesService {

    @GET("movie/top_rated")
    suspend fun getTopRatedMovies(
        @Query("api_key") apiKey:String = BuildConfig.API_KEY
    ) : Response<MoviesResponse>
}