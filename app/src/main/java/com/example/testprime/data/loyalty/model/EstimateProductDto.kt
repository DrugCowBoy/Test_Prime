package com.example.testprime.data.loyalty.model


import com.google.gson.annotations.SerializedName

data class EstimateProductDto(
    @SerializedName("id")
    val id: String?,
    @SerializedName("image")
    val image: String?,
    @SerializedName("name")
    val name: String?,
    @SerializedName("payment_date")
    val paymentDate: String?
)