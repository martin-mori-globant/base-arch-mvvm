package com.example.basemvvmexample.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import com.example.basemvvmexample.data.api.ApiHelper
import com.example.basemvvmexample.data.api.response.Resource
import com.example.basemvvmexample.data.local.BreedDao
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

    fun getDogBreedsFromApi() = liveData(Dispatchers.IO) {
        emit(Resource.loading(data = null))
        try {
            val dogBreedsResponse = mainRepository.getDogBreeds()
            val breedsRoomList = dogBreedsResponse.body()?.message?.keys?.toList()?.map { it -> BreedRoom(it) }
            _dogBreedsLiveData.postValue(breedsRoomList)
            // saveOnDatabase(list)
            emit(Resource.success(data = dogBreedsResponse))
        } catch (exception: Exception) {
            emit(Resource.error(data = null, message = exception.message ?: "Error Occurred!"))
        }
    }

    fun saveOnDatabase(list: List<String>) {
        viewModelScope.launch(Dispatchers.IO) {
            mainRepository.insert(list.map { BreedRoom(it) })
        }
    }

    fun loadOnDataBase() {
        viewModelScope.launch(Dispatchers.IO) {
            mainRepository.loadBreeds()
        }
    }

    fun deleteLocal() {
        viewModelScope.launch(Dispatchers.IO) {
            mainRepository.deleteLocal()
        }
    }

    class Factory(private val apiHelper: ApiHelper, private val breedDao: BreedDao) : ViewModelProvider.Factory {

        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(BreedViewModel::class.java)) {
                return BreedViewModel(MainRepository(apiHelper, breedDao)) as T
            }
            throw IllegalArgumentException("Unknown class name")
        }
    }
}
