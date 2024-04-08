package com.example.testprime.domain.product.repository

import com.example.testprime.domain.product.model.ProductItem

interface ProductRepository {

    suspend fun getProductItem(idProduct: String, token: String): ProductItem

}