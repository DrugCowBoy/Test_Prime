package com.example.testprime.data.menu.datasource

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import com.dot.prime.data.menu.model.ResultCategoriesProductDto
import com.dot.prime.data.menu.model.ResultProductsDto
import com.example.testprime.data.common.network.PrimeService
import retrofit2.Response
import javax.inject.Inject

class MenuDataSourceImpl @Inject constructor(
    private val primeService: PrimeService,
    private val dataStore: DataStore<Preferences>
): MenuDataSource {

    override suspend fun getCategoriesProduct(token: String): Response<ResultCategoriesProductDto> {
        return primeService.getCategoriesProduct(token = token)
    }

    override suspend fun getProducts(search: String, token: String): Response<ResultProductsDto> {
        return primeService.getProducts(search = search, token = token)
    }

}