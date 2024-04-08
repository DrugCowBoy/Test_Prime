package com.example.testprime.data.loyalty.model


import com.example.testprime.data.loyalty.model.BannerDto
import com.google.gson.annotations.SerializedName

data class ResultBannersDto(
    @SerializedName("result")
    val result: List<BannerDto?>?
)