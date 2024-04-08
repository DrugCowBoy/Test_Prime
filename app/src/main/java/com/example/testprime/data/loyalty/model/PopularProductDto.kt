package com.example.testprime.data.loyalty.model

import com.example.testprime.data.loyalty.model.CategoryProductDto
import com.example.testprime.data.loyalty.model.ImageDto
import com.example.testprime.data.loyalty.model.OptionsDto
import com.google.gson.annotations.SerializedName

data class PopularProductDto(
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