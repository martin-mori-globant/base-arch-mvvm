package com.example.basemvvmexample.data.api

import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

object RetrofitFactory {
    private const val BASE_URL = "https://dog.ceo/api/breeds/"

    fun getApiService(): ApiService {
        return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(MoshiConverterFactory.create())
                .build().create(ApiService::class.java)
    }
}
