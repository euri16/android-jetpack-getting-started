package com.jetpackinitialexample.app.data.network.responses

import com.google.gson.annotations.SerializedName

data class MoviesResponse(
    var page: Long = 0,

    var results: List<Movie>? = null,

    @SerializedName("total_pages")
    var totalPages: Long = 0,

    @SerializedName("total_results")
    var totalResults: Long = 0
)
