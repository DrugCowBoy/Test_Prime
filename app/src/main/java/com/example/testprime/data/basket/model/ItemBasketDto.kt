package com.example.testprime.data.basket.model


import com.example.testprime.data.basket.model.BasketProductDto
import com.google.gson.annotations.SerializedName

data class ItemBasketDto(
    @SerializedName("product")
    val product: BasketProductDto?,
    @SerializedName("quantity")
    val quantity: Int?
)