package com.example.testprime.data.profile.datasource

import com.example.testprime.data.profile.model.FullUserDataDto
import com.example.testprime.data.profile.model.UserInfoPref
import com.example.testprime.data.profile.model.AccessAndRefreshDto
import com.example.testprime.data.profile.model.ChangeEmailBodyDto
import com.example.testprime.data.profile.model.CodeBodyDto
import com.example.testprime.data.profile.model.DataChangeTokenDto
import com.example.testprime.data.profile.model.PhoneBodyDto
import com.example.testprime.data.profile.model.PhoneTokenDto
import com.example.testprime.data.profile.model.RefreshBodyDto
import com.example.testprime.data.profile.model.UserBodyDto
import com.example.testprime.data.profile.model.UserDataDto
import com.example.testprime.data.profile.model.UserLoginDto
import kotlinx.coroutines.flow.Flow
import okhttp3.ResponseBody
import retrofit2.Response

interface ProfileDataSource {

    // Retrofit
    suspend fun getPhoneCheck(phoneBodyDto: PhoneBodyDto): Response<PhoneTokenDto>

    suspend fun getCodeCheck(codeBodyDto: CodeBodyDto): Response<UserLoginDto>

    suspend fun refreshToken(refreshBodyDto: RefreshBodyDto): Response<UserLoginDto>

    suspend fun putUserData(token: String, userBodyDto: UserBodyDto): Response<UserDataDto>

    suspend fun managerPutUserData(token: String, userBodyDto: UserBodyDto): Response<UserDataDto>

    suspend fun getUserData(token: String): Response<FullUserDataDto>

    suspend fun deleteUserData(token: String): Response<ResponseBody>

    suspend fun changeUserPhone(token: String, phoneBodyDto: PhoneBodyDto): Response<DataChangeTokenDto>

    suspend fun changeUserPhoneCode(token: String, codeBodyDto: CodeBodyDto): Response<AccessAndRefreshDto>

    suspend fun verifyEmail(token: String): Response<DataChangeTokenDto>

    suspend fun changeEmail(token: String, changeEmailBodyDto: ChangeEmailBodyDto): Response<DataChangeTokenDto>



    // DataStore
    suspend fun saveUserInfo(userInfoPref: UserInfoPref)

    fun readUserInfo(): Flow<UserInfoPref>

    suspend fun saveToken(token: String)

    fun readToken(): Flow<String>

    suspend fun saveRefresh(refresh: String)

    fun readRefresh(): Flow<String>

    suspend fun saveEmptyUserInfo()

}