package com.example.testprime.domain.basket.model

import com.example.testprime.domain.basket.model.BasketProduct

data class ItemBasket(
    val product: BasketProduct?,
    val quantity: Int?
)