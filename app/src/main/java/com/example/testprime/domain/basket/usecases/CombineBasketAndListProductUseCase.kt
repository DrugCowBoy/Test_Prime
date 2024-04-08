package com.example.testprime.domain.basket.usecases

import com.example.testprime.domain.basket.model.ItemBasket
import com.example.testprime.domain.menu.model.CategoryWithProducts
import com.example.testprime.domain.menu.model.Product
import javax.inject.Inject

class CombineBasketAndListProductUseCase @Inject constructor() {

    operator fun invoke(listProduct: List<CategoryWithProducts>, listBasket: List<ItemBasket>): List<CategoryWithProducts>{

        val list = listProduct.map { category ->
            val subList = category.listProducts.map{ product->
                var editProduct = product
                for (basketProduct in listBasket){
                    if (basketProduct.product?.id == product.id){
                        editProduct = product.copy(basketQuantity = basketProduct.quantity ?: 0)
                    }
                }
                return@map editProduct
            }
            return@map category.copy(listProducts = subList)
        }

        return list
    }

}