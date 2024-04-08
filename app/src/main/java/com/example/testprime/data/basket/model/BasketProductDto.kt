package com.example.testprime.data.basket.model


import com.google.gson.annotations.SerializedName

data class BasketProductDto(
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
    @SerializedName("options")
    val options: OptionsDto?,
    @SerializedName("price")
    val price: Double?
)