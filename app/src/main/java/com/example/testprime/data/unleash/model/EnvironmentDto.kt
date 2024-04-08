package com.example.testprime.data.unleash.model


import com.google.gson.annotations.SerializedName

data class EnvironmentDto(
    @SerializedName("enabled")
    val enabled: Boolean?,
    @SerializedName("name")
    val name: String?,
    @SerializedName("sortOrder")
    val sortOrder: Int?,
    @SerializedName("type")
    val type: String?
)