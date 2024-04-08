package com.dot.prime.data.menu.model


import com.google.gson.annotations.SerializedName

data class ResultCategoriesProductDto(
    @SerializedName("result")
    val result: List<CategoryProductDto?>?
)