package com.example.testprime.data.basket.model


import com.google.gson.annotations.SerializedName

data class ResultBasketProductsDto(
    @SerializedName("result")
    val result: ResultDto?
)