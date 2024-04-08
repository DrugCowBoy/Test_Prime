package com.example.testprime.data.profile.model


import com.google.gson.annotations.SerializedName

data class UserDataResultDto(
    @SerializedName("birth_date")
    val birthDate: String?,
    @SerializedName("email")
    val email: String?,
    @SerializedName("id")
    val id: String?,
    @SerializedName("name")
    val name: String?,
    @SerializedName("phone")
    val phone: String?,
    @SerializedName("roles")
    val roles: List<String?>?,
    @SerializedName("status")
    val status: String?,
    @SerializedName("sex")
    val idGender: Int?,
    @SerializedName("allow_sms")
    val allowSms: Boolean?,
    @SerializedName("allow_email")
    val allowEmail: Boolean?,
    @SerializedName("notification")
    val allowPush: Boolean?,
    @SerializedName("email_verified")
    val emailVerified: Boolean?
)