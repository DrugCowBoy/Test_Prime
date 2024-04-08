package com.example.testprime.data.basket.model


import com.google.gson.annotations.SerializedName

data class DeleteBasketBodyDto(
    @SerializedName("product_id")
    val productId: String
)