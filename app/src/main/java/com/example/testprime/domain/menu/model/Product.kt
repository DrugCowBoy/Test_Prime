package com.example.testprime.domain.menu.model

import com.example.testprime.domain.menu.model.CategoryProduct
import com.example.testprime.domain.menu.model.Image
import com.example.testprime.domain.menu.model.Options

data class Product(
    val category: CategoryProduct?,
    val description: String?,
    val id: String?,
    val images: List<Image?>?,
    val measure: String?,
    val name: String?,
    val options: Options?,
    val price: Double?,
    val isCategoryEnabled: Boolean = false,
    val basketQuantity: Int = 0
)