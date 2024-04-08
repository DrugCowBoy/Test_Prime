package com.example.testprime.domain.menu.usecases

import com.example.testprime.domain.menu.model.CategoryWithProducts
import com.example.testprime.domain.menu.model.Product
import com.example.testprime.domain.menu.repository.MenuRepository
import java.util.*
import javax.inject.Inject

class GetProductsUseCase @Inject constructor(private val repository: MenuRepository) {

    suspend operator fun invoke(search: String = "", token: String): List<CategoryWithProducts>{
        val response = repository.getProducts(
            search = editInput(input = search),
            token = token
        )
        return convertToCategoryWithProductsList(listProduct = response)
    }

    private fun editInput(input: String): String{
        // Delete spaces
        val inputTrim = input.trim().replace("\\s+".toRegex(), " ")
        // Low register
        val inputTrimLow = inputTrim.lowercase(Locale.ROOT)
        return inputTrimLow
    }

    private fun convertToCategoryWithProductsList(listProduct: List<Product>): List<CategoryWithProducts>{
        val groupedProducts = listProduct.groupBy { it.category?.id }
        val listCategoryWithProducts = groupedProducts.map { (categoryId, products) ->
            CategoryWithProducts(
                categoryId = categoryId,
                categoryName = products.firstOrNull()?.category?.name,
                listProducts = products
            )
        }
        val listFilterCategoryWithProducts = listCategoryWithProducts.filter { categoryWithProducts ->
            categoryWithProducts.categoryId != null
        }
        return listFilterCategoryWithProducts
    }

}