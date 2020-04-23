package com.example.basemvvmexample.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import com.example.basemvvmexample.data.api.response.Resource
import com.example.basemvvmexample.data.local.BreedRoom
import com.example.basemvvmexample.data.repository.MainRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class BreedViewModel(private val mainRepository: MainRepository) : BaseViewModel() {

    private var _dogBreedsLiveData: MutableLiveData<List<BreedRoom>> = MutableLiveData()
    var dogBreedsLiveData: LiveData<List<BreedRoom>> = _dogBreedsLiveData

    init {
        dogBreedsLiveData = mainRepository.breeds
    }

    fun getDogBreedsFromRepo() = liveData(Dispatchers.IO) {
        try {
            if (dogBreedsLiveData.value.isNullOrEmpty()) {
                emit(Resource.loading(data = null))
                val dogBreedsResponse = mainRepository.getDogBreeds()
                val breedsRoomList = dogBreedsResponse.body()?.message?.keys?.toList()?.map { it -> BreedRoom(it) }
                _dogBreedsLiveData.postValue(breedsRoomList)
                saveOnDatabase(breedsRoomList)
                emit(Resource.success(data = dogBreedsResponse))
            }
        } catch (exception: Exception) {
            emit(Resource.error(data = null, message = exception.message ?: "Error Occurred!"))
        }
    }

    private fun saveOnDatabase(list: List<BreedRoom>?) {
        viewModelScope.launch(Dispatchers.IO) {
            list?.let { mainRepository.insert(it) }
        }
    }

    fun deleteLocal() {
        viewModelScope.launch(Dispatchers.IO) {
            mainRepository.deleteLocal()
        }
    }
}
