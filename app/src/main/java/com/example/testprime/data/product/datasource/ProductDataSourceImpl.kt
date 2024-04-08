package com.example.testprime.data.product.datasource

import com.example.testprime.data.product.model.ProductItemDto
import com.example.testprime.data.common.network.PrimeService
import retrofit2.Response
import javax.inject.Inject

class ProductDataSourceImpl @Inject constructor(private val primeService: PrimeService):
    ProductDataSource {

    override suspend fun getProductItem(idProduct: String, token: String): Response<ProductItemDto> {
        return primeService.getProductItem(idProduct = idProduct, token = token)
    }
}