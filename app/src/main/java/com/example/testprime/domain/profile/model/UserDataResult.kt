package com.example.testprime.domain.profile.model


data class UserDataResult(
    val birthDate: String?,
    val email: String?,
    val id: String?,
    val name: String?,
    val phone: String?,
    val roles: List<String?>?,
    val status: String?,
    val idGender: Int?,
    val allowSms: Boolean?,
    val allowEmail: Boolean?,
    val allowPush: Boolean?,
    val emailVerified: Boolean?
)