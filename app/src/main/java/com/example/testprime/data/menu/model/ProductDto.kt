package com.dot.prime.data.menu.model

import com.google.gson.annotations.SerializedName

data class ProductDto(
    @SerializedName("category")
    val category: CategoryProductDto?,
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
    @SerializedName("options")
    val options: OptionsDto?,
    @SerializedName("price")
    val price: Double?
)