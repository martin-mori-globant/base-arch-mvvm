package com.example.basemvvmexample.data.api

import com.example.basemvvmexample.data.model.DogImage
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {
    @GET("image/random")
    suspend fun getDogImage(): Response<DogImage>
}
