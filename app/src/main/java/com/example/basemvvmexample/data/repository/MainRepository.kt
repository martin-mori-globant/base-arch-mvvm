package com.example.basemvvmexample.data.repository

import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import com.example.basemvvmexample.data.api.ApiHelper
import com.example.basemvvmexample.data.api.response.DogBreedResponse
import com.example.basemvvmexample.data.local.BreedDao
import com.example.basemvvmexample.data.local.BreedRoom
import retrofit2.Response

class MainRepository(private val apiHelper: ApiHelper, private val breedDao: BreedDao) {

    val breeds: LiveData<List<BreedRoom>> = breedDao.getBreedsAlphabeticOrder()

    suspend fun getDogImage() = apiHelper.getDogImage()
    suspend fun getDogBreeds(): Response<DogBreedResponse> = apiHelper.getDogBreeds()

    @WorkerThread
    suspend fun insert(breeds: List<BreedRoom>) {
        breedDao.insertAll(breeds)
    }

    fun loadBreeds(): LiveData<List<BreedRoom>> {
        return breedDao.getBreedsAlphabeticOrder()
    }

    suspend fun deleteLocal() {
        breedDao.deleteAll()
    }
}
