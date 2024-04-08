package com.example.testprime.data.product.model


import com.example.testprime.data.product.model.CategoryDto
import com.example.testprime.data.product.model.ImageDto
import com.example.testprime.data.product.model.OptionsDto
import com.google.gson.annotations.SerializedName

data class ProductItemDto(
    @SerializedName("category")
    val category: CategoryDto?,
    @SerializedName("description")
    val description: String?,
    @SerializedName("id")
    val id: String?,
    @SerializedName("images")
    val images: List<ImageDto?>?,
    @SerializedName("measure")
    val measure: String?,
    @SerializedName("name")
    val name: String?,
    @SerializedName("price")
    val price: Double?,
    @SerializedName("options")
    val options: OptionsDto?
)