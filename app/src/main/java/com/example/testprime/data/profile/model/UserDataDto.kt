package com.example.testprime.data.profile.model


import com.google.gson.annotations.SerializedName

data class UserDataDto(
    @SerializedName("result")
    val result: UserDataResultDto?
)