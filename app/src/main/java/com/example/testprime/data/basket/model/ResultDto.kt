package com.example.testprime.data.basket.model


import com.example.testprime.data.basket.model.ItemBasketDto
import com.google.gson.annotations.SerializedName

data class ResultDto(
    @SerializedName("items")
    val items: List<ItemBasketDto?>?
)