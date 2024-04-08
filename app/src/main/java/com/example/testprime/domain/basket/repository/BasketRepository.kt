package com.example.testprime.domain.basket.repository

import com.example.testprime.domain.basket.model.AddAndDecreaseBasketBody
import com.example.testprime.domain.basket.model.DeleteBasketBody
import com.example.testprime.domain.basket.model.ItemBasket

interface BasketRepository {

    suspend fun getBasketProducts(token: String): List<ItemBasket>

    suspend fun addAndDecreaseBasket(token: String, basketBody: AddAndDecreaseBasketBody): List<ItemBasket>

    suspend fun deleteBasket(token: String, basketBody: DeleteBasketBody): List<ItemBasket>

    suspend fun clearAllBasket(token: String): List<ItemBasket>

}