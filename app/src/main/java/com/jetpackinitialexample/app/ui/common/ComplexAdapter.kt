package com.jetpackinitialexample.app.ui.common

import android.widget.Switch
import androidx.annotation.LayoutRes
import com.outcomehealth.jetpackinitialexample.R

class ComplexAdapter(
    callback: ((item: String) -> Unit)? = null
) : BaseRecyclerViewAdapter<String>(callback) {

    override fun getLayoutRes(): Int = R.layout.my_string_list_item

    override fun onBindViewHolder(holder: DataBindingViewHolder<String>, position: Int) {
        super.onBindViewHolder(holder, position)

        val mySwitch = holder.itemView.findViewById<Switch>(R.id.my_switch)
        mySwitch.setOnCheckedChangeListener { compoundButton, b ->


        }


    }
}