package com.example.testprime.data.menu.repository

import com.example.testprime.data.menu.datasource.MenuDataSource
import com.example.testprime.data.menu.mapper.MenuMapper
import com.example.testprime.domain.menu.model.CategoryProduct
import com.example.testprime.domain.menu.model.Product
import com.example.testprime.domain.menu.repository.MenuRepository
import com.example.testprime.data.common.network.SafeApiRequest
import javax.inject.Inject

class MenuRepositoryImpl @Inject constructor(
    private val menuDataSource: MenuDataSource,
    private val mapper: MenuMapper
): MenuRepository, SafeApiRequest() {

    override suspend fun getCategoriesProduct(token: String): List<CategoryProduct> {
        val response = safeApiRequest { menuDataSource.getCategoriesProduct(token = token) }
        return mapper.mapCategoriesProductDtoToEntity(response)
    }

    override suspend fun getProducts(search: String, token: String): List<Product> {
        val response = safeApiRequest { menuDataSource.getProducts(search = search, token = token) }
        return mapper.mapProductsDtoToEntity(response)
    }

}