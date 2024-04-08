package com.example.testprime.data.basket.model


import com.google.gson.annotations.SerializedName

data class AddAndDecreaseBasketBodyDto(
    @SerializedName("product_id")
    val productId: String,
    @SerializedName("quantity")
    val quantity: Int
)