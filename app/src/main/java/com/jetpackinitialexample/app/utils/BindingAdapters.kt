package com.jetpackinitialexample.app.utils

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.jetpackinitialexample.app.ui.common.BaseRecyclerViewAdapter

/**
 * Created by euryperez on 2019-10-01.
 */

object BindingAdapters {

    @Suppress("UNCHECKED_CAST")
    @BindingAdapter("android:data")
    @JvmStatic
    fun <T> setRecyclerViewData(recyclerView: RecyclerView, items: List<T>?) {
        items?.let {
            (recyclerView.adapter as? BaseRecyclerViewAdapter<T>)?.apply {
                clear()
                addData(items)
            }
        }
    }

    @Suppress("UNCHECKED_CAST")
    @BindingAdapter("android:liveData")
    @JvmStatic
    fun <T> setRecyclerViewData(recyclerView: RecyclerView, items: LiveData<List<T>>?) {
        items?.value?.let { itemList ->
            (recyclerView.adapter as? BaseRecyclerViewAdapter<T>)?.apply {
                clear()
                addData(itemList)
            }
        }
    }

    @Suppress("UNCHECKED_CAST")
    @BindingAdapter("android:duration")
    @JvmStatic
    fun setDurationText(textView: TextView, duration: Long?) {
        duration?.let {
            textView.text = duration.durationString
        }
    }

    @BindingAdapter("android:visibility")
    @JvmStatic
    fun setViewVisibility(view: View, visible: Boolean) {
        view.visibility = if (visible) View.VISIBLE else View.GONE
    }

    @BindingAdapter("android:text")
    @JvmStatic
    fun setDoubleText(textView: TextView, value:Double?) {
        value?.let {
            textView.text = value.toString()
        }
    }

    @BindingAdapter("android:imageUrl")
    @JvmStatic
    fun setImageUrl(imageView: ImageView, url:String?) {
        url?.let {
            Glide.with(imageView.context)
                .load(url)
                .into(imageView)
        }
    }

    @BindingAdapter("android:fadeVisible")
    @JvmStatic
    fun setFadeVisible(view: View, visible: Boolean? = true) {
        if (view.tag == null) {
            view.tag = true
            view.visibility = if (visible == true) View.VISIBLE else View.GONE
        } else {
            view.animate().cancel()
            if (visible == true) {
                if (view.visibility == View.GONE)
                    view.fadeIn()
            } else {
                if (view.visibility == View.VISIBLE)
                    view.fadeOut()
            }
        }
    }
}