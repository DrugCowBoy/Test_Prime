package com.example.testprime.data.profile.mapper

import com.example.testprime.data.profile.model.FullUserDataDto
import com.example.testprime.data.profile.model.AccessAndRefreshDto
import com.example.testprime.data.profile.model.UserInfoPref
import com.example.testprime.data.profile.model.UserDataDto
import com.example.testprime.data.profile.model.UserLoginDto
import com.example.testprime.domain.profile.model.FullUserDataResult
import com.example.testprime.domain.profile.model.AccessAndRefresh
import com.example.testprime.domain.profile.model.UserInfo
import com.example.testprime.domain.profile.model.UserDataResult
import com.example.testprime.domain.profile.model.UserLoginResult
import javax.inject.Inject

class ProfileMapper @Inject constructor() {

    fun mapUserLoginDtoToEntity(userLoginDto: UserLoginDto) = UserLoginResult(
        access = userLoginDto.result?.access,
        birthDate = userLoginDto.result?.birthDate,
        email = userLoginDto.result?.email,
        id = userLoginDto.result?.id,
        name = userLoginDto.result?.name,
        phone = userLoginDto.result?.phone,
        refresh = userLoginDto.result?.refresh,
        roles = userLoginDto.result?.roles,
        status = userLoginDto.result?.status
    )

    fun mapUserDataDtoToEntity(userDataDto: UserDataDto) = UserDataResult(
        birthDate = userDataDto.result?.birthDate,
        email = userDataDto.result?.email,
        id = userDataDto.result?.id,
        name = userDataDto.result?.name,
        phone = userDataDto.result?.phone,
        roles = userDataDto.result?.roles,
        status = userDataDto.result?.status,
        idGender = userDataDto.result?.idGender,
        allowSms = userDataDto.result?.allowSms,
        allowEmail = userDataDto.result?.allowEmail,
        allowPush = userDataDto.result?.allowPush,
        emailVerified = userDataDto.result?.emailVerified
    )

    fun mapUserInfoEntityToPref(userInfo: UserInfo) = UserInfoPref(
        id = userInfo.id,
        phone = userInfo.phone,
        name = userInfo.name,
        email = userInfo.email,
        birthDate = userInfo.birthDate,
        status = userInfo.status,
        idGender = userInfo.idGender,
        allowSms = userInfo.allowSms,
        allowEmail = userInfo.allowEmail,
        allowPush = userInfo.allowPush,
        emailVerified = userInfo.emailVerified
    )

    fun mapUserInfoPrefToEntity(userInfoPref: UserInfoPref) = UserInfo(
        id = userInfoPref.id,
        phone = userInfoPref.phone,
        name = userInfoPref.name,
        email = userInfoPref.email,
        birthDate = userInfoPref.birthDate,
        status = userInfoPref.status,
        idGender = userInfoPref.idGender,
        allowSms = userInfoPref.allowSms,
        allowEmail = userInfoPref.allowEmail,
        allowPush = userInfoPref.allowPush,
        emailVerified = userInfoPref.emailVerified
    )

    fun mapFullUserDataDtoToEntity(fullUserDataDto: FullUserDataDto) = FullUserDataResult(
        activeBalance = fullUserDataDto.result?.activeBalance,
        balance = fullUserDataDto.result?.balance,
        birthDate = fullUserDataDto.result?.birthDate,
        cardNumber = fullUserDataDto.result?.cardNumber,
        email = fullUserDataDto.result?.email,
        id = fullUserDataDto.result?.id,
        name = fullUserDataDto.result?.name,
        participantStatus = fullUserDataDto.result?.participantStatus,
        phone = fullUserDataDto.result?.phone,
        roles = fullUserDataDto.result?.roles,
        status = fullUserDataDto.result?.status,
        statusActiveBalance = fullUserDataDto.result?.statusActiveBalance,
        statusBalance = fullUserDataDto.result?.statusBalance,
        birthDaySalesStatus = fullUserDataDto.result?.birthDaySalesStatus,
        userStatus = fullUserDataDto.result?.userStatus,
        userSumStatus = fullUserDataDto.result?.userSumStatus,
        colorStatus = fullUserDataDto.result?.colorStatus,
        fontColorStatus = fullUserDataDto.result?.fontColorStatus,
        nextUserStatus = fullUserDataDto.result?.nextUserStatus,
        rangeSum = fullUserDataDto.result?.rangeSum,
        idGender = fullUserDataDto.result?.idGender,
        allowSms = fullUserDataDto.result?.allowSms,
        allowEmail = fullUserDataDto.result?.allowEmail,
        allowPush = fullUserDataDto.result?.allowPush,
        emailVerified = fullUserDataDto.result?.emailVerified
    )

    fun mapAccessAndRefreshDtoToEntity(accessAndRefreshDto: AccessAndRefreshDto) = AccessAndRefresh(
        access = accessAndRefreshDto.access,
        refresh = accessAndRefreshDto.refresh
    )
}