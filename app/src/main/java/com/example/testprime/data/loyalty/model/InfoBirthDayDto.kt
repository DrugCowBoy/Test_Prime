package com.example.testprime.data.loyalty.model


import com.google.gson.annotations.SerializedName

data class InfoBirthDayDto(
    @SerializedName("long_info")
    val longInfo: String?,
    @SerializedName("short_info")
    val shortInfo: String?,
    @SerializedName("sub")
    val sub: String?,
    @SerializedName("title")
    val title: String?
)