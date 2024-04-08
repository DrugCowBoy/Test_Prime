package com.example.testprime.domain.profile.model


data class UserLoginResult(
    val access: String?,
    val birthDate: String?,
    val email: String?,
    val id: String?,
    val name: String?,
    val phone: String?,
    val refresh: String?,
    val roles: List<String?>?,
    val status: String?
)