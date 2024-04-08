package com.example.testprime.domain.basket.usecases

import com.example.testprime.domain.basket.model.DeleteBasketBody
import com.example.testprime.domain.basket.model.ItemBasket
import com.example.testprime.domain.basket.repository.BasketRepository
import javax.inject.Inject

class DeleteItemBasketUseCase @Inject constructor(private val repository: BasketRepository) {

    suspend operator fun invoke(token: String, basketBody: DeleteBasketBody): List<ItemBasket>{
        return repository.deleteBasket(token = token, basketBody = basketBody)
    }

}