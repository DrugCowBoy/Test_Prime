package com.example.testprime.data.loyalty.model


import com.google.gson.annotations.SerializedName

data class EstimateBodyDto(
    @SerializedName("grade")
    val grade: Int?,
    @SerializedName("user_comment")
    val userComment: String?
)