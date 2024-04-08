package com.example.testprime.data.product.repository

import com.example.testprime.data.product.datasource.ProductDataSource
import com.example.testprime.data.product.mapper.ProductMapper
import com.example.testprime.domain.product.model.ProductItem
import com.example.testprime.domain.product.repository.ProductRepository
import com.example.testprime.data.common.network.SafeApiRequest
import javax.inject.Inject

class ProductRepositoryImpl @Inject constructor(
    private val productDataSource: ProductDataSource,
    private val mapper: ProductMapper
): ProductRepository, SafeApiRequest() {

    override suspend fun getProductItem(idProduct: String, token: String): ProductItem {
        val response = safeApiRequest { productDataSource.getProductItem(idProduct = idProduct, token = token) }
        return mapper.mapProductItemDtoToEntity(response)
    }
}