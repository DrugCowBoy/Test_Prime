package com.dot.prime.data.profile.repository

import com.example.testprime.data.profile.datasource.ProfileDataSource
import com.example.testprime.data.profile.mapper.ProfileMapper
import com.example.testprime.data.profile.model.CodeBodyDto
import com.example.testprime.data.profile.model.PhoneBodyDto
import com.example.testprime.data.profile.model.UserBodyDto
import com.example.testprime.data.profile.model.ChangeEmailBodyDto
import com.example.testprime.data.profile.model.RefreshBodyDto
import com.example.testprime.domain.profile.model.FullUserDataResult
import com.example.testprime.domain.profile.model.AccessAndRefresh
import com.example.testprime.domain.profile.model.UserInfo
import com.example.testprime.domain.profile.model.UserDataResult
import com.example.testprime.domain.profile.model.UserLoginResult
import com.example.testprime.domain.profile.repository.ProfileRepository
import com.example.testprime.data.common.network.SafeApiRequest
import com.example.testprime.di.IoDispatcher
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import javax.inject.Inject

class ProfileRepositoryImpl @Inject constructor(
    private val profileDataSource: ProfileDataSource,
    private val mapper: ProfileMapper,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher
): ProfileRepository, SafeApiRequest() {

    // Retrofit
    override suspend fun getPhoneCheck(phone: String): String {
        val response = safeApiRequest {
            profileDataSource.getPhoneCheck(PhoneBodyDto(phone))
        }
        return response.token
    }

    override suspend fun getCodeCheck(token: String, code: String): UserLoginResult {
        val response = safeApiRequest {
            profileDataSource.getCodeCheck(
                CodeBodyDto(
                    token = token,
                    code = code
                )
            )
        }
        return mapper.mapUserLoginDtoToEntity(response)
    }

    override suspend fun refreshToken(refresh: String): UserLoginResult {
        val response = safeApiRequest {
            profileDataSource.refreshToken(
                RefreshBodyDto(
                    refresh = refresh
                )
            )
        }
        return mapper.mapUserLoginDtoToEntity(response)
    }

    override suspend fun putUserData(
        token: String,
        name: String,
        email: String?,
        birthDate: String,
        idGender: Int,
        allowEmail: Boolean,
        allowSms: Boolean,
        allowPush: Boolean
    ): UserDataResult {
        val response = safeApiRequest {
            profileDataSource.putUserData(
                token = token,
                userBodyDto = UserBodyDto(
                    birthDate = birthDate,
                    email = email,
                    name = name,
                    idGender = idGender,
                    allowEmail = allowEmail,
                    allowSms = allowSms,
                    allowPush = allowPush,
                    source = 2// 1- ios, 2 - android
                )
            )
        }
        return mapper.mapUserDataDtoToEntity(response)
    }

    override suspend fun managerPutUserData(
        token: String,
        name: String,
        email: String?,
        birthDate: String,
        idGender: Int,
        allowEmail: Boolean,
        allowSms: Boolean,
        allowPush: Boolean
    ): UserDataResult {
        val response = safeApiRequest {
            profileDataSource.managerPutUserData(
                token = token,
                userBodyDto = UserBodyDto(
                    birthDate = birthDate,
                    email = email,
                    name = name,
                    idGender = idGender,
                    allowEmail = allowEmail,
                    allowSms = allowSms,
                    allowPush = allowPush,
                    source = 2// 1- ios, 2 - android
                )
            )
        }
        return mapper.mapUserDataDtoToEntity(response)
    }

    override suspend fun getUserData(token: String): FullUserDataResult {
        val response = safeApiRequest { profileDataSource.getUserData(token = token) }
        return mapper.mapFullUserDataDtoToEntity(response)
    }

    override suspend fun deleteUserData(token: String): Boolean {
        return successApiRequest { profileDataSource.deleteUserData(token = token)}
    }

    override suspend fun changeUserPhone(token: String, phone: String): String {
        val response = safeApiRequest {
            profileDataSource.changeUserPhone(
                token = token,
                phoneBodyDto = PhoneBodyDto(phone)
            )
        }
        return response.token
    }

    override suspend fun changeUserPhoneCode(token: String, tokenPhone: String, code: String): AccessAndRefresh {
        val response = safeApiRequest {
            profileDataSource.changeUserPhoneCode(
                token = token,
                codeBodyDto = CodeBodyDto(token = tokenPhone, code = code)
            )
        }
        return mapper.mapAccessAndRefreshDtoToEntity(response)
    }

    override suspend fun verifyEmail(token: String): String {
        val response = safeApiRequest {
            profileDataSource.verifyEmail(token = token)
        }
        return response.token
    }

    override suspend fun changeEmail(token: String, email: String): String {
        val response = safeApiRequest {
            profileDataSource.changeEmail(
                token = token,
                changeEmailBodyDto = ChangeEmailBodyDto(email = email)
            )
        }
        return response.token
    }

    // DataStore
    override suspend fun saveUserInfo(userInfo: UserInfo) {
        withContext(ioDispatcher){
            profileDataSource.saveUserInfo(mapper.mapUserInfoEntityToPref(userInfo))
        }
    }

    override fun readUserInfo(): Flow<UserInfo> {
        return profileDataSource.readUserInfo().map { userInfoPref ->
            mapper.mapUserInfoPrefToEntity(userInfoPref)
        }
    }

    override suspend fun saveToken(token: String) {
        withContext(ioDispatcher){
            profileDataSource.saveToken(token)
        }
    }

    override fun readToken(): Flow<String> {
        return profileDataSource.readToken()
    }

    override suspend fun saveRefresh(refresh: String) {
        withContext(ioDispatcher){
            profileDataSource.saveRefresh(refresh)
        }
    }

    override fun readRefresh(): Flow<String> {
        return profileDataSource.readRefresh()
    }

    override suspend fun saveEmptyUserInfo() {
        withContext(ioDispatcher){
            profileDataSource.saveEmptyUserInfo()
        }
    }

}