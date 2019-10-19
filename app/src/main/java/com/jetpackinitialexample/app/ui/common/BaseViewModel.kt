package com.jetpackinitialexample.app.ui.common

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.jetpackinitialexample.app.utils.SingleLiveEvent

abstract class BaseViewModel(app:Application) : AndroidViewModel(app) {
    protected val _isLoading = MutableLiveData<Boolean>().apply { value = true }
    open val isLoading: LiveData<Boolean>
        get() = _isLoading

    protected val _isError = MutableLiveData<Boolean>().apply { value = false }
    open val isError: LiveData<Boolean>
        get() = _isError

    val navigationCommand: SingleLiveEvent<NavigationCommand> = SingleLiveEvent()
}