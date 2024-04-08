package com.example.testprime.data.profile.model


import com.google.gson.annotations.SerializedName

data class UserLoginResultDto(
    @SerializedName("access")
    val access: String?,
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
    @SerializedName("refresh")
    val refresh: String?,
    @SerializedName("roles")
    val roles: List<String?>?,
    @SerializedName("status")
    val status: String?
)