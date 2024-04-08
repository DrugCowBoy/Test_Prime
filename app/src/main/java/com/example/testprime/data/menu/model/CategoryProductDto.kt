package com.dot.prime.data.menu.model


import com.google.gson.annotations.SerializedName

data class CategoryProductDto(
    @SerializedName("description")
    val description: String?,
    @SerializedName("id")
    val id: String?,
    @SerializedName("main_image_link")
    val mainImageLink: String?,
    @SerializedName("name")
    val name: String?
)