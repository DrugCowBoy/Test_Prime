package com.example.testprime.data.profile.model

import com.google.gson.annotations.SerializedName

data class RefreshBodyDto(
    @SerializedName("refresh")
    val refresh: String,
)