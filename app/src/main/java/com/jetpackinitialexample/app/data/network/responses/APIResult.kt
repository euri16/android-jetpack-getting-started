package com.jetpackinitialexample.app.data.network.responses

import okhttp3.ResponseBody

sealed class APIResult<out T: Any> {
    data class Success<out T : Any>(val data: T) : APIResult<T>()
    data class Error(val errorBody: ResponseBody?, val statusCode:Int? = null) : APIResult<Nothing>()

    companion object {
        const val HTTP_NOT_FOUND_CODE = 404
    }
}