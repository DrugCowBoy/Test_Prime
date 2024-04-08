package com.example.testprime.data.menu.datasource

import androidx.paging.PagingData
import com.dot.prime.data.menu.model.ProductDto
import com.dot.prime.data.menu.model.ResultCategoriesProductDto
import com.dot.prime.data.menu.model.ResultProductsDto
import kotlinx.coroutines.flow.Flow
import retrofit2.Response

interface MenuDataSource {

    suspend fun getCategoriesProduct(token: String): Response<ResultCategoriesProductDto>

    suspend fun getProducts(search: String, token: String): Response<ResultProductsDto>

}