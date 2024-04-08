package com.example.testprime.domain.menu.repository

import androidx.paging.PagingData
import com.example.testprime.domain.menu.model.CategoryProduct
import com.example.testprime.domain.menu.model.Product
import kotlinx.coroutines.flow.Flow

interface MenuRepository {

    suspend fun getCategoriesProduct(token: String): List<CategoryProduct>

    suspend fun getProducts(search: String, token: String): List<Product>

}