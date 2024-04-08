package com.example.testprime.domain.loyalty.model


import com.google.gson.annotations.SerializedName

data class Image(
    @SerializedName("link")
    val link: String?
)