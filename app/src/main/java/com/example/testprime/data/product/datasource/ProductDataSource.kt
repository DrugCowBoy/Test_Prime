package com.example.testprime.data.product.datasource

import com.example.testprime.data.product.model.ProductItemDto
import retrofit2.Response

interface ProductDataSource {

    suspend fun getProductItem(idProduct: String, token: String): Response<ProductItemDto>

}