package com.example.testprime.domain.basket.usecases

import com.example.testprime.domain.basket.model.ItemBasket
import com.example.testprime.domain.basket.repository.BasketRepository
import javax.inject.Inject

class ClearAllBasketUseCase @Inject constructor(private val repository: BasketRepository) {

    suspend operator fun invoke(token: String): List<ItemBasket>{
        return repository.clearAllBasket(token = token)
    }

}