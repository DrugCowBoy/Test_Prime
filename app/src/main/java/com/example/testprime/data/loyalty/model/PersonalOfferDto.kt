package com.example.testprime.data.loyalty.model


import com.google.gson.annotations.SerializedName

data class PersonalOfferDto(
    @SerializedName("finish_date_time")
    val finishDateTime: String?,
    @SerializedName("id")
    val id: String?,
    @SerializedName("is_active")
    val isActive: Boolean?,
    @SerializedName("max_quantity")
    val maxQuantity: Int?,
    @SerializedName("product_description")
    val productDescription: Any?,
    @SerializedName("product_icon_url")
    val productIconUrl: String?,
    @SerializedName("product_name")
    val productName: String?,
    @SerializedName("start_date_time")
    val startDateTime: String?,
    @SerializedName("value")
    val value: Int?
)