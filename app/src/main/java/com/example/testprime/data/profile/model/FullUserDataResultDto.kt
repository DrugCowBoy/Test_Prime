package com.example.testprime.data.profile.model


import com.google.gson.annotations.SerializedName

data class FullUserDataResultDto(
    @SerializedName("active_balance")
    val activeBalance: String?,
    @SerializedName("balance")
    val balance: String?,
    @SerializedName("birth_date")
    val birthDate: String?,
    @SerializedName("card_number")
    val cardNumber: String?,
    @SerializedName("email")
    val email: String?,
    @SerializedName("id")
    val id: String?,
    @SerializedName("name")
    val name: String?,
    @SerializedName("participant_status")
    val participantStatus: String?,
    @SerializedName("phone")
    val phone: String?,
    @SerializedName("roles")
    val roles: List<String?>?,
    @SerializedName("status")
    val status: String?,
    @SerializedName("status_active_balance")
    val statusActiveBalance: String?,
    @SerializedName("status_balance")
    val statusBalance: String?,
    @SerializedName("birth_day_sales_status")
    val birthDaySalesStatus: String?,
    @SerializedName("user_status")
    val userStatus: String?,
    @SerializedName("user_sum_status")
    val userSumStatus: String?,
    @SerializedName("color_status")
    val colorStatus: String?,
    @SerializedName("font_color_status")
    val fontColorStatus: String?,
    @SerializedName("next_user_status")
    val nextUserStatus: String?,
    @SerializedName("range_sum")
    val rangeSum: List<Int>?,
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