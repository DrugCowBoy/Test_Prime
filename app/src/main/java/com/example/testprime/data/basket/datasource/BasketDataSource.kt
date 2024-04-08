package com.example.testprime.data.basket.datasource

import com.example.testprime.data.basket.model.AddAndDecreaseBasketBodyDto
import com.example.testprime.data.basket.model.DeleteBasketBodyDto
import com.example.testprime.data.basket.model.ResultBasketProductsDto
import retrofit2.Response

interface BasketDataSource {

    suspend fun getBasketProducts(token: String): Response<ResultBasketProductsDto>

    suspend fun addAndDecreaseBasket(token: String, basketBodyDto: AddAndDecreaseBasketBodyDto): Response<ResultBasketProductsDto>

    suspend fun deleteBasket(token: String, basketBodyDto: DeleteBasketBodyDto): Response<ResultBasketProductsDto>

    suspend fun clearAllBasket(token: String): Response<ResultBasketProductsDto>

}