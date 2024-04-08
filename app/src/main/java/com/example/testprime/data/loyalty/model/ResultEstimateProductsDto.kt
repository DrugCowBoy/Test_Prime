package com.example.testprime.data.loyalty.model


import com.example.testprime.data.loyalty.model.EstimateProductDto
import com.example.testprime.data.loyalty.model.PaginationDto
import com.google.gson.annotations.SerializedName

data class ResultEstimateProductsDto(
    @SerializedName("pagination")
    val pagination: PaginationDto?,
    @SerializedName("result")
    val result: List<EstimateProductDto?>?
)