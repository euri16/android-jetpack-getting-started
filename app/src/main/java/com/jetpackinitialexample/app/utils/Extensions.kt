package com.jetpackinitialexample.app.utils

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.content.Context
import android.net.ConnectivityManager
import android.util.Log
import android.view.View
import androidx.annotation.DimenRes
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.jetpackinitialexample.app.ui.common.BaseRecyclerViewAdapter
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.io.IOException
import java.net.HttpURLConnection
import java.net.URL


/**
 * Created by euryperez on 2019-10-01.
 */

const val REACHABILITY_SERVER = "http://google.com"

fun <T> RecyclerView.setup(
    adapter: BaseRecyclerViewAdapter<T>,
    columns: Int = 1
) {
    this.apply {
        layoutManager = if (columns > 1) GridLayoutManager(this.context, columns)
        else LinearLayoutManager(this.context)
        this.adapter = adapter
    }
}

fun Context.dpToPx(@DimenRes dimens: Int): Int {
    return resources.getDimensionPixelSize(dimens)
}

val Long.durationString: String
    get() = String.format("%02d:%02d", remainingMinutes, remainingSeconds)

val Long.remainingMinutes
    get() = this / 1000 / 60

val Long.remainingSeconds
    get() = this / 1000 % 60

fun View.fadeIn() {
    this.visibility = View.VISIBLE
    this.alpha = 0f
    this.animate().alpha(1f).setListener(object : AnimatorListenerAdapter() {
        override fun onAnimationEnd(animation: Animator) {
            this@fadeIn.alpha = 1f
        }
    })
}

fun View.fadeOut() {
    this.animate().alpha(0f).setListener(object : AnimatorListenerAdapter() {
        override fun onAnimationEnd(animation: Animator) {
            this@fadeOut.alpha = 1f
            this@fadeOut.visibility = View.GONE
        }
    })
}
private fun hasNetworkAvailable(context: Context): Boolean {
    val service = Context.CONNECTIVITY_SERVICE
    val manager = context.getSystemService(service) as ConnectivityManager?
    val network = manager?.activeNetworkInfo
    Log.d("Extensions.kt", "hasNetworkAvailable: ${(network != null)}")
    return (network != null)
}

fun Context.isConnected(isOnline: (Boolean) -> Unit) {
    fun callIsOnline(isOnline: Boolean) {
        GlobalScope.launch(Dispatchers.Main) {
            isOnline(isOnline)
        }
    }

    GlobalScope.launch {
        if (hasNetworkAvailable(this@isConnected)) {
            try {
                val connection = URL(REACHABILITY_SERVER).openConnection() as HttpURLConnection
                connection.setRequestProperty("User-Agent", "Test")
                connection.setRequestProperty("Connection", "close")
                connection.connectTimeout = 1500
                connection.connect()
                Log.d("Context.isConnected", "hasInternetConnected: ${(connection.responseCode == 200)}")
                callIsOnline(connection.responseCode == 200)
                return@launch
            } catch (e: IOException) {
                Log.e("Context.isConnected", "Error checking internet connection", e)
            }
        } else {
            Log.w("Context.isConnected", "No network available!")
        }
        Log.d("Context.isConnected", "hasInternetConnected: false")
        callIsOnline(false)
    }
}