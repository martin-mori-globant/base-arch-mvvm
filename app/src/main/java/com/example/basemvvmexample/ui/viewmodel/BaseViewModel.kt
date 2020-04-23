package com.example.basemvvmexample.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlin.coroutines.CoroutineContext
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancel

open class BaseViewModel : ViewModel(), CoroutineScope {

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Default + SupervisorJob()

    protected var mainStateNetworkingResponse = MutableLiveData<String>()

    val mainStateNetworking: LiveData<String>
        get() {
            return mainStateNetworkingResponse
        }

    override fun onCleared() {
        coroutineContext.cancel()
        super.onCleared()
    }
}
