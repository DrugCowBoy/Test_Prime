package com.dot.prime.data.menu.model


import com.google.gson.annotations.SerializedName

data class ResultProductsDto(
    @SerializedName("pagination")
    val pagination: PaginationDto?,
    @SerializedName("result")
    val result: List<ProductDto>?
)