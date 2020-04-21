package com.example.basemvvmexample.data.repository

import com.example.basemvvmexample.data.api.ApiHelper
import com.example.basemvvmexample.data.api.response.DogBreedResponse
import retrofit2.Response

class MainRepository(private val apiHelper: ApiHelper) {

    suspend fun getDogImage() = apiHelper.getDogImage()
    suspend fun getDogBreeds(): Response<DogBreedResponse> = apiHelper.getDogBreeds()

    // @WorkerThread
    // suspend fun insert(breeds: List<BreedRoom>) {
    //     breedDao.insertAll(breeds)
    // }
}
