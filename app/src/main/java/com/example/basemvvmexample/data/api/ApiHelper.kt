package com.example.basemvvmexample.data.api

class ApiHelper(private val apiService: ApiService) {
    suspend fun getDogImage() = apiService.getDogImage()
    suspend fun getDogBreedsApiCall() = apiService.getDogBreeds()
}
