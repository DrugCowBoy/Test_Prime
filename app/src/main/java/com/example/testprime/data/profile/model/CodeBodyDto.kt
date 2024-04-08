package com.example.testprime.data.profile.model


import com.google.gson.annotations.SerializedName

data class CodeBodyDto(
    @SerializedName("code")
    val code: String,
    @SerializedName("token")
    val token: String
)