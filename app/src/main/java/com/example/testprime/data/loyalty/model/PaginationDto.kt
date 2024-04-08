package com.example.testprime.data.loyalty.model


import com.google.gson.annotations.SerializedName

data class PaginationDto(
    @SerializedName("limit")
    val limit: Int?,
    @SerializedName("page")
    val page: Int?,
    @SerializedName("total")
    val total: Int?
)