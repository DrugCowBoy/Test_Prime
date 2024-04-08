package com.example.testprime.data.profile.model

import com.google.gson.annotations.SerializedName

data class AccessAndRefreshDto(
    @SerializedName("access")
    val access: String,
    @SerializedName("refresh")
    val refresh: String
)