package com.jetpackinitialexample.app.ui.common

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController

abstract class BaseFragment : Fragment() {

    abstract val _viewModel:BaseViewModel?

    override fun onStart() {
        super.onStart()

        _viewModel?.navigationCommand?.observe(this, Observer { command ->
            when (command) {
                is NavigationCommand.To ->
                    findNavController().navigate(command.directions)
                is NavigationCommand.Back ->
                    findNavController().popBackStack()
                is NavigationCommand.BackTo ->
                    findNavController().popBackStack(command.destinationId, false)
            }
        })
    }

    fun setToolbarVisibility(isVisible:Boolean) {
        val toolbar = (activity as? AppCompatActivity)?.supportActionBar

        if(isVisible) {
            toolbar?.show()
        } else {
            toolbar?.hide()
        }
    }
}