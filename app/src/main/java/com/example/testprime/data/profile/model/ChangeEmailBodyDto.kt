package com.example.testprime.data.profile.model

import com.google.gson.annotations.SerializedName

data class ChangeEmailBodyDto(
    @SerializedName("email")
    val email: String
)
