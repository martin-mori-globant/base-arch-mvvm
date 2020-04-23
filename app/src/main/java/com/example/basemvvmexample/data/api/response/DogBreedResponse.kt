package com.example.basemvvmexample.data.api.response

import com.squareup.moshi.Json

data class DogBreedResponse(
    @Json(name = "message") val message: Map<String, List<Any>>,
    @Json(name = "status") val status: String
)
