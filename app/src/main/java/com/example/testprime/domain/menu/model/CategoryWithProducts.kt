package com.example.testprime.domain.menu.model

data class CategoryWithProducts(
    val categoryId: String?,
    val categoryName: String?,
    val listProducts: List<Product>
)
