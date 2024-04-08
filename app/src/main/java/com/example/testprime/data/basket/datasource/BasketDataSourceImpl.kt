package com.example.testprime.data.basket.datasource

import com.example.testprime.data.basket.model.AddAndDecreaseBasketBodyDto
import com.example.testprime.data.basket.model.DeleteBasketBodyDto
import com.example.testprime.data.basket.model.ResultBasketProductsDto
import com.example.testprime.data.common.network.PrimeService
import retrofit2.Response
import javax.inject.Inject

class BasketDataSourceImpl @Inject constructor(private val primeService: PrimeService):
    BasketDataSource {

    override suspend fun getBasketProducts(token: String): Response<ResultBasketProductsDto> {
        return primeService.getBasketProducts(token = token)
    }

    override suspend fun addAndDecreaseBasket(token: String, basketBodyDto: AddAndDecreaseBasketBodyDto
    ): Response<ResultBasketProductsDto> {
        return primeService.addAndDecreaseBasket(token = token, basketBodyDto = basketBodyDto)
    }

    override suspend fun deleteBasket(token: String, basketBodyDto: DeleteBasketBodyDto
    ): Response<ResultBasketProductsDto> {
        return primeService.deleteBasket(token = token, basketBodyDto = basketBodyDto)
    }

    override suspend fun clearAllBasket(token: String): Response<ResultBasketProductsDto> {
        return primeService.clearAllBasket(token = token)
    }
}