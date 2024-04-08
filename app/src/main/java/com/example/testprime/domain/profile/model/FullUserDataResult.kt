package com.example.testprime.domain.profile.model

import com.google.gson.annotations.SerializedName

data class FullUserDataResult(
    val activeBalance: String?,
    val balance: String?,
    val birthDate: String?,
    val cardNumber: String?,
    val email: String?,
    val id: String?,
    val name: String?,
    val participantStatus: String?,
    val phone: String?,
    val roles: List<String?>?,
    val status: String?,
    val statusActiveBalance: String?,
    val statusBalance: String?,
    val birthDaySalesStatus: String?,
    val userStatus: String?,
    val userSumStatus: String?,
    val colorStatus: String?,
    val fontColorStatus: String?,
    val nextUserStatus: String?,
    val rangeSum: List<Int>?,
    val idGender: Int?,
    val allowSms: Boolean?,
    val allowEmail: Boolean?,
    val allowPush: Boolean?,
    val emailVerified: Boolean?
)
