package com.example.testprime.domain.menu.usecases

import com.example.testprime.domain.menu.model.CategoryProduct
import com.example.testprime.domain.menu.repository.MenuRepository
import javax.inject.Inject

class GetCategoriesProductUseCase @Inject constructor(private val repository: MenuRepository) {

    suspend operator fun invoke(nameAllCategories: String, token: String): List<CategoryProduct> {
        val categories = repository.getCategoriesProduct(token = token).toMutableList()
        categories.add(
            index = 0,
            element = CategoryProduct(
                description = null,
                id = null,
                mainImageLink = null,
                name = nameAllCategories
            )
        )
        return categories
    }

}