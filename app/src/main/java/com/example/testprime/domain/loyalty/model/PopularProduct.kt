package com.example.testprime.domain.loyalty.model

import com.example.testprime.domain.loyalty.model.CategoryProduct
import com.example.testprime.domain.loyalty.model.Image
import com.example.testprime.domain.loyalty.model.Options

data class PopularProduct(
    val category: CategoryProduct?,
    val description: String?,
    val id: String?,
    val images: List<Image?>?,
    val measure: String?,
    val name: String?,
    val options: Options?,
    val price: Double?,
    val basketQuantity: Int = 0
)