package com.example.testprime.data.profile.model


import com.google.gson.annotations.SerializedName

data class UserLoginDto(
    @SerializedName("result")
    val result: UserLoginResultDto?
)