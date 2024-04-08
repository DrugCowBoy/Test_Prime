package com.example.testprime.domain.loyalty.model


import com.google.gson.annotations.SerializedName

data class PersonalOffer(
    val finishDateTime: String?,
    val id: String?,
    val isActive: Boolean?,
    val maxQuantity: Int?,
    val productDescription: Any?,
    val productIconUrl: String?,
    val productName: String?,
    val startDateTime: String?,
    val value: Int?
)