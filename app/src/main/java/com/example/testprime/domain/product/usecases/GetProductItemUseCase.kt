package com.example.testprime.domain.product.usecases

import com.example.testprime.domain.product.model.ProductItem
import com.example.testprime.domain.product.repository.ProductRepository
import javax.inject.Inject

class GetProductItemUseCase @Inject constructor(private val repository: ProductRepository) {

    suspend operator fun invoke(idProduct: String, token: String): ProductItem {
        return repository.getProductItem(idProduct = idProduct, token = token)
    }

}