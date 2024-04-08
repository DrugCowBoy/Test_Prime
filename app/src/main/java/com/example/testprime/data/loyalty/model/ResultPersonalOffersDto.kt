package com.example.testprime.data.loyalty.model


import com.example.testprime.data.loyalty.model.PersonalOfferDto
import com.google.gson.annotations.SerializedName

data class ResultPersonalOffersDto(
    @SerializedName("result")
    val personalOffer: List<PersonalOfferDto?>?
)