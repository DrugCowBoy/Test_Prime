package com.example.testprime.domain.basket.usecases

import com.example.testprime.domain.basket.model.AddAndDecreaseBasketBody
import com.example.testprime.domain.basket.model.DeleteBasketBody
import com.example.testprime.domain.basket.model.ItemBasket
import com.example.testprime.domain.basket.repository.BasketRepository
import javax.inject.Inject

class ChangeItemBasketUseCase @Inject constructor(
    private val repository: BasketRepository,
    private val deleteItemBasketUseCase: DeleteItemBasketUseCase
) {

    suspend operator fun invoke(token: String, basketBody: AddAndDecreaseBasketBody): List<ItemBasket>{
        if (basketBody.quantity == 0){
            return deleteItemBasketUseCase(token = token, basketBody = mapAddAndDecreaseToDeleteBasketBody(basketBody))
        }else {
            return repository.addAndDecreaseBasket(token = token, basketBody = basketBody)
        }
    }

    private fun mapAddAndDecreaseToDeleteBasketBody(addAndDecreaseBasketBody: AddAndDecreaseBasketBody) = DeleteBasketBody(
        productId = addAndDecreaseBasketBody.productId
    )

}