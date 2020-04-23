package com.example.basemvvmexample.data.api.response

import com.google.gson.annotations.SerializedName

data class DogImageResponse(
    @SerializedName("message") val message: String,
    @SerializedName("status") val status: String
)
