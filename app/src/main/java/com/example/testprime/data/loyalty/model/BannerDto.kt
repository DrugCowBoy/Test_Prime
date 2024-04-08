package com.example.testprime.data.loyalty.model


import com.google.gson.annotations.SerializedName

data class BannerDto(
    @SerializedName("description")
    val description: String?,
    @SerializedName("target")
    val target: String?,
    @SerializedName("title")
    val title: String?,
    @SerializedName("url")
    val url: String?
)