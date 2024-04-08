package com.example.testprime.data.unleash.model


import com.example.testprime.data.unleash.model.FeatureDto
import com.google.gson.annotations.SerializedName

data class UnleashDto(
    @SerializedName("features")
    val features: List<FeatureDto?>?,
    @SerializedName("version")
    val version: Int?
)