package com.example.testprime.data.profile.model


import com.google.gson.annotations.SerializedName

data class FullUserDataDto(
    @SerializedName("result")
    val result: FullUserDataResultDto?
)