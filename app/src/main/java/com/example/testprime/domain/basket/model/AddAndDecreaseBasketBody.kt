package com.example.testprime.domain.basket.model


data class AddAndDecreaseBasketBody(
    val productId: String,
    val quantity: Int
)