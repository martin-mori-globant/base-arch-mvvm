package com.example.basemvvmexample.data.repository

import com.example.basemvvmexample.data.api.ApiHelper

class MainRepository(private val apiHelper: ApiHelper) {

    suspend fun getDogImage() = apiHelper.getDogImage()
}