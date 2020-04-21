package com.example.basemvvmexample.data.api

import com.example.basemvvmexample.data.api.response.DogBreedResponse
import com.example.basemvvmexample.data.api.response.DogImageResponse
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {
    @GET("image/random")
    suspend fun getDogImage(): Response<DogImageResponse>

    @GET("list/all")
    suspend fun getDogBreeds(): Response<DogBreedResponse>
}
