package com.example.testprime.domain.profile.model

data class UserInfo(
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