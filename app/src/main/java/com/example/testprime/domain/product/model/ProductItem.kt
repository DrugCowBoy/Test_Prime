package com.example.testprime.domain.product.model

import com.example.testprime.domain.product.model.Category
import com.example.testprime.domain.product.model.Image
import com.example.testprime.domain.product.model.Options


data class ProductItem(
    val category: Category?,
    val description: String?,
    val id: String?,
    val images: List<Image?>?,
    val measure: String?,
    val name: String?,
    val price: Double?,
    val options: Options?,
    val isFavorite: Boolean = false
)