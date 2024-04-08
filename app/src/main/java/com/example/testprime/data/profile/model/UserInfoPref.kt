package com.example.testprime.data.profile.model

data class UserInfoPref(
    val id: String?,
    val phone: String?,
    val name: String?,
    val email: String?,
    val birthDate: String?,
    val status: String?,
    val idGender: Int?,
    val allowSms: Boolean?,
    val allowEmail: Boolean?,
    val allowPush: Boolean?,
    val emailVerified: Boolean?
)