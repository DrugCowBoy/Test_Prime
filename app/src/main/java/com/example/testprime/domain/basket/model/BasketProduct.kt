package com.example.testprime.domain.basket.model

data class BasketProduct(
    val category: Category?,
    val description: String?,
    val id: String?,
    val images: List<Image?>?,
    val measure: String?,
    val name: String?,
    val options: Options?,
    val price: Double?
)