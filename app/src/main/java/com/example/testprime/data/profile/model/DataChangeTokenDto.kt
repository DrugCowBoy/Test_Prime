package com.example.testprime.data.profile.model

import com.google.gson.annotations.SerializedName

data class DataChangeTokenDto(
    @SerializedName("result")
    val token: String
)
