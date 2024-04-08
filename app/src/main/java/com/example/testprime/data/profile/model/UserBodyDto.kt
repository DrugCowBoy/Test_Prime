package com.example.testprime.data.profile.model


import com.google.gson.annotations.SerializedName

data class UserBodyDto(
    @SerializedName("birth_date")
    val birthDate: String,
    @SerializedName("email")
    val email: String?,
    @SerializedName("name")
    val name: String,
    @SerializedName("sex")
    val idGender: Int,
    @SerializedName("allow_email")
    val allowEmail: Boolean,
    @SerializedName("allow_sms")
    val allowSms: Boolean,
    @SerializedName("allow_push")
    val allowPush: Boolean,
    @SerializedName("source")
    val source: Int
)