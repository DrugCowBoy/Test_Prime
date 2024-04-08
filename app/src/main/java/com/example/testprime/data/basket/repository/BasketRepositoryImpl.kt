package com.example.testprime.data.basket.repository

import com.example.testprime.data.basket.datasource.BasketDataSource
import com.example.testprime.data.basket.mapper.BasketMapper
import com.example.testprime.domain.basket.model.AddAndDecreaseBasketBody
import com.example.testprime.domain.basket.model.DeleteBasketBody
import com.example.testprime.domain.basket.model.ItemBasket
import com.example.testprime.domain.basket.repository.BasketRepository
import com.example.testprime.data.common.network.SafeApiRequest
import javax.inject.Inject

class BasketRepositoryImpl @Inject constructor(
    private val basketDataSource: BasketDataSource,
    private val mapper: BasketMapper
): BasketRepository, SafeApiRequest() {

    override suspend fun getBasketProducts(token: String): List<ItemBasket> {
        val response = safeApiRequest { basketDataSource.getBasketProducts(token = token) }
        return mapper.mapBasketDtoToEntity(response)
    }

    override suspend fun addAndDecreaseBasket(token: String, basketBody: AddAndDecreaseBasketBody): List<ItemBasket> {
        val response = safeApiRequest {
            basketDataSource.addAndDecreaseBasket(
                token = token,
                basketBodyDto = mapper.mapAddAndDecreaseBasketBodyEntityToDto(basketBody = basketBody)
            )
        }
        return mapper.mapBasketDtoToEntity(response)
    }

    override suspend fun deleteBasket(token: String, basketBody: DeleteBasketBody): List<ItemBasket> {
        val response = safeApiRequest {
            basketDataSource.deleteBasket(
                token = token,
                basketBodyDto = mapper.mapDeleteBasketBodyEntityToDto(basketBody = basketBody)
            )
        }
        return mapper.mapBasketDtoToEntity(response)
    }

    override suspend fun clearAllBasket(token: String): List<ItemBasket> {
        val response = safeApiRequest {
            basketDataSource.clearAllBasket(
                token = token
            )
        }
        return mapper.mapBasketDtoToEntity(response)
    }
}