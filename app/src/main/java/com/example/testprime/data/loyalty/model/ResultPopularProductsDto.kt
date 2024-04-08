package com.example.testprime.data.loyalty.model


import com.example.testprime.data.loyalty.model.PaginationDto
import com.example.testprime.data.loyalty.model.PopularProductDto
import com.google.gson.annotations.SerializedName

data class ResultPopularProductsDto(
    @SerializedName("pagination")
    val pagination: PaginationDto?,
    @SerializedName("result")
    val result: List<PopularProductDto>?
)