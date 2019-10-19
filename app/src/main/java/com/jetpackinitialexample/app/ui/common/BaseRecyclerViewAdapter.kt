package com.jetpackinitialexample.app.ui.common

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.RecyclerView
import java.util.*

abstract class BaseRecyclerViewAdapter<T>(private val callback:((item:T) -> Unit)? = null) : RecyclerView.Adapter<DataBindingViewHolder<T>>() {

    private var _items: MutableList<T> = mutableListOf()

    /**
     * Returns the _items data
     */
    private val items: List<T>?
        get() = this._items

    /**
     * Public constructor, if used _items will be filled with last
     * by default.
     */
    constructor() : this(null) {
        _items = ArrayList()
    }

    /**
     * Public constructor when _items data is provided
     *
     * @param items to initialize the data
     */
    constructor(items: MutableList<T>) : this(null) {
        this._items = items
    }

    override fun getItemCount() =_items.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataBindingViewHolder<T> {
        val layoutInflater = LayoutInflater.from(parent.context)

        val binding = DataBindingUtil
            .inflate<ViewDataBinding>(layoutInflater, getLayoutRes(), parent, false)

        binding.lifecycleOwner = getLifecycleOwner()

        return DataBindingViewHolder(binding)
    }

    override fun onBindViewHolder(holder: DataBindingViewHolder<T>, position: Int) {
        val item = getItem(position)
        holder.bind(item)
        holder.itemView.setOnClickListener {
            callback?.invoke(item)
        }
    }

    fun getItem(position: Int) = _items[position]


    /**
     * Adds data to the actual Dataset
     *
     * @param item to be merged
     */
    fun addData(item: T) {
        _items.add(item)
        notifyDataSetChanged()
    }

    /**
     * Adds data to the actual Dataset
     *
     * @param items to be merged
     */
    fun addData(items: List<T>) {
        _items.addAll(items)
        notifyDataSetChanged()
    }

    /**
     * Removes a specific item in the data
     *
     * @param item to be removed
     */
    fun removeItem(item: T) {
        _items.remove(item)
        notifyDataSetChanged()
    }

    /**
     * Removes a post from the data
     *
     * @param items posts to remove
     */
    fun removeItem(items: List<T>) {
        _items.removeAll(items)
        notifyDataSetChanged()
    }

    /**
     * Updates a specific item in the data
     *
     * @param item  to be updated
     * @param index of the item in the data
     */
    fun putItem(item: T, index: Int) {
        _items.set(index, item)
        notifyItemChanged(index)
    }

    /**
     * Returns the index of a specified item
     *
     * @param item of the specific item
     */
    fun getIndex(item: T): Int {
        return _items.indexOf(getItem(item))
    }

    /**
     * Clears the _items data
     */
    fun clear() {
        _items.clear()
        notifyDataSetChanged()
    }

    /**
     * Returns a specific item
     *
     * @param itemId of the specific item
     */
    fun getItem(item: T) = this.items?.find { it == item }


    @LayoutRes
    abstract fun getLayoutRes(): Int

    open fun getLifecycleOwner() : LifecycleOwner? {
        return null
    }
}

