package com.jetpackinitialexample.app.ui.common

import androidx.annotation.LayoutRes

class SimpleAdapter<T>(
    @LayoutRes private val _layoutRes: Int,
    callback: ((item: T) -> Unit)? = null
) : BaseRecyclerViewAdapter<T>(callback) {

    override fun getLayoutRes(): Int = _layoutRes
}