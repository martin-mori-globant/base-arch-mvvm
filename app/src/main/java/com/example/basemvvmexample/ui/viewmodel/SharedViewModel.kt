package com.example.basemvvmexample.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SharedViewModel : ViewModel() {

    val breed = MutableLiveData<String>("EMPTY_VAL")
    val subBreed = MutableLiveData<String>("EMPTY_VAL")

    fun breed(item: String) {
        breed.value = item
    }

    fun subBreed(item: String) {
        subBreed.value = item
    }
}
