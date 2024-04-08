package com.example.testprime.domain.profile.repository

import com.example.testprime.domain.profile.model.FullUserDataResult
import com.example.testprime.domain.profile.model.AccessAndRefresh
import com.example.testprime.domain.profile.model.UserInfo
import com.example.testprime.domain.profile.model.UserDataResult
import com.example.testprime.domain.profile.model.UserLoginResult
import kotlinx.coroutines.flow.Flow

interface ProfileRepository {

    // Retrofit
    suspend fun getPhoneCheck(phone: String): String

    suspend fun getCodeCheck(token: String, code: String): UserLoginResult

    suspend fun refreshToken(refresh: String): UserLoginResult

    suspend fun putUserData(
        token: String,
        name: String,
        email: String?,
        birthDate: String,
        idGender: Int,
        allowEmail: Boolean,
        allowSms: Boolean,
        allowPush: Boolean
    ): UserDataResult

    suspend fun managerPutUserData(
        token: String,
        name: String,
        email: String?,
        birthDate: String,
        idGender: Int,
        allowEmail: Boolean,
        allowSms: Boolean,
        allowPush: Boolean
    ): UserDataResult

    suspend fun getUserData(token: String): FullUserDataResult

    suspend fun deleteUserData(token: String): Boolean

    suspend fun changeUserPhone(token: String, phone: String): String

    suspend fun changeUserPhoneCode(token: String, tokenPhone: String, code: String): AccessAndRefresh

    suspend fun verifyEmail(token: String): String

    suspend fun changeEmail(token: String, email: String): String



    // DataStore
    suspend fun saveUserInfo(userInfo: UserInfo)

    fun readUserInfo(): Flow<UserInfo>

    suspend fun saveToken(token: String)

    fun readToken(): Flow<String>

    suspend fun saveRefresh(refresh: String)

    fun readRefresh(): Flow<String>

    suspend fun saveEmptyUserInfo()

}