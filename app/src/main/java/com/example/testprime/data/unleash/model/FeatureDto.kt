package com.example.testprime.data.unleash.model


import com.google.gson.annotations.SerializedName

data class FeatureDto(
    @SerializedName("createdAt")
    val createdAt: String?,
    @SerializedName("environments")
    val environments: List<EnvironmentDto?>?,
    @SerializedName("lastSeenAt")
    val lastSeenAt: String?,
    @SerializedName("name")
    val name: String?,
    @SerializedName("stale")
    val stale: Boolean?,
    @SerializedName("type")
    val type: String?
)