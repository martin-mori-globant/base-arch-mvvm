package com.example.basemvvmexample.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.liveData
import com.example.basemvvmexample.data.api.ApiHelper
import com.example.basemvvmexample.data.model.Resource
import com.example.basemvvmexample.data.repository.MainRepository
import kotlinx.coroutines.Dispatchers

class MainViewModel(private val mainRepository: MainRepository) : ViewModel() {
    private var _dogUrl = MutableLiveData("dog")

    val dogUrl: LiveData<String> = _dogUrl

    fun getDogImage() = liveData(Dispatchers.IO) {
        emit(Resource.loading(data = null))
        try {
            val dogImageResponse = mainRepository.getDogImage()
            _dogUrl.postValue(dogImageResponse.body()?.message)
            emit(Resource.success(data = dogImageResponse))
        } catch (exception: Exception) {
            emit(Resource.error(data = null, message = exception.message ?: "Error Occurred!"))
        }
    }

    class Factory(private val apiHelper: ApiHelper) : ViewModelProvider.Factory {

        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
                return MainViewModel(MainRepository(apiHelper)) as T
            }
            throw IllegalArgumentException("Unknown class name")
        }
    }
}
