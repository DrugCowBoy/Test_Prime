package com.example.testprime.data.profile.datasource

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.*
import com.example.testprime.common.enum_classes.IdGender
import com.example.testprime.data.profile.model.FullUserDataDto
import com.example.testprime.data.profile.model.UserInfoPref
import com.example.testprime.common.Constants
import com.example.testprime.data.common.network.PrimeService
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
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import okhttp3.ResponseBody
import retrofit2.Response
import javax.inject.Inject

class ProfileDataSourceImpl @Inject constructor(
    private val primeService: PrimeService,
    private val dataStore: DataStore<Preferences>
): ProfileDataSource {

    // Retrofit
    override suspend fun getPhoneCheck(phoneBodyDto: PhoneBodyDto): Response<PhoneTokenDto> {
        return primeService.getPhoneCheck(phoneBodyDto)
    }

    override suspend fun getCodeCheck(codeBodyDto: CodeBodyDto): Response<UserLoginDto> {
        return primeService.getCodeCheck(codeBodyDto)
    }

    override suspend fun refreshToken(refreshBodyDto: RefreshBodyDto): Response<UserLoginDto> {
        return primeService.refreshToken(refreshBodyDto)
    }

    override suspend fun putUserData(token: String, userBodyDto: UserBodyDto): Response<UserDataDto> {
        return primeService.putUserData(token = token, userBodyDto = userBodyDto)
    }

    override suspend fun managerPutUserData(token: String, userBodyDto: UserBodyDto): Response<UserDataDto> {
        return primeService.managerPutUserData(token = token, userBodyDto = userBodyDto)
    }

    override suspend fun getUserData(token: String): Response<FullUserDataDto> {
        return primeService.getUserData(token = token)
    }

    override suspend fun deleteUserData(token: String): Response<ResponseBody> {
        return primeService.deleteUserData(token = token)
    }

    override suspend fun changeUserPhone(
        token: String,
        phoneBodyDto: PhoneBodyDto
    ): Response<DataChangeTokenDto> {
        return primeService.changeUserPhone(token = token, phoneBodyDto = phoneBodyDto)
    }

    override suspend fun changeUserPhoneCode(
        token: String,
        codeBodyDto: CodeBodyDto
    ): Response<AccessAndRefreshDto> {
        return primeService.changeUserPhoneCode(token = token, codeBodyDto = codeBodyDto)
    }

    override suspend fun verifyEmail(token: String): Response<DataChangeTokenDto> {
        return primeService.verifyEmail(token = token)
    }

    override suspend fun changeEmail(
        token: String,
        changeEmailBodyDto: ChangeEmailBodyDto
    ): Response<DataChangeTokenDto> {
        return primeService.changeEmail(token = token, changeEmailBodyDto = changeEmailBodyDto)
    }



    // DataStore
    object PreferenceKeys{
        // UserInfo
        val id = stringPreferencesKey(Constants.USER_ID)
        val phone = stringPreferencesKey(Constants.USER_PHONE)
        val name = stringPreferencesKey(Constants.USER_NAME)
        val email = stringPreferencesKey(Constants.USER_EMAIl)
        val birthDate = stringPreferencesKey(Constants.USER_BIRTH_DATE)
        val status = stringPreferencesKey(Constants.USER_STATUS)
        val idGender = intPreferencesKey(Constants.USER_ID_GENDER)
        val allowSms = booleanPreferencesKey(Constants.USER_ALLOW_SMS)
        val allowEmail = booleanPreferencesKey(Constants.USER_ALLOW_EMAIL)
        val allowPush = booleanPreferencesKey(Constants.USER_ALLOW_PUSH)
        val emailVerified = booleanPreferencesKey(Constants.USER_EMAIL_VERIFIED)
        // Token
        val token = stringPreferencesKey(Constants.USER_TOKEN)
        // Refresh
        val refresh = stringPreferencesKey(Constants.USER_REFRESH)
    }

    override suspend fun saveUserInfo(userInfoPref: UserInfoPref) {
        dataStore.edit { mutablePreferences ->
            mutablePreferences[PreferenceKeys.id] = userInfoPref.id ?: Constants.DEFAULT_PREFERENCE
            mutablePreferences[PreferenceKeys.phone] = userInfoPref.phone ?: Constants.DEFAULT_PREFERENCE
            mutablePreferences[PreferenceKeys.name] = userInfoPref.name ?: Constants.DEFAULT_PREFERENCE
            mutablePreferences[PreferenceKeys.email] = userInfoPref.email ?: Constants.DEFAULT_PREFERENCE
            mutablePreferences[PreferenceKeys.birthDate] = userInfoPref.birthDate ?: Constants.DEFAULT_PREFERENCE
            mutablePreferences[PreferenceKeys.status] = userInfoPref.status ?: Constants.DEFAULT_PREFERENCE
            mutablePreferences[PreferenceKeys.idGender] = userInfoPref.idGender ?: IdGender.Male.idGender
            mutablePreferences[PreferenceKeys.allowSms] = userInfoPref.allowSms ?: true
            mutablePreferences[PreferenceKeys.allowEmail] = userInfoPref.allowEmail ?: true
            mutablePreferences[PreferenceKeys.allowPush] = userInfoPref.allowPush ?: true
            mutablePreferences[PreferenceKeys.emailVerified] = userInfoPref.emailVerified ?: true
        }
    }

    override fun readUserInfo(): Flow<UserInfoPref> = dataStore.data
        .catch {
            emit(emptyPreferences())
        }
        .map { preferences ->
            val id = preferences[PreferenceKeys.id] ?: Constants.DEFAULT_PREFERENCE
            val phone = preferences[PreferenceKeys.phone] ?: Constants.DEFAULT_PREFERENCE
            val name = preferences[PreferenceKeys.name] ?: Constants.DEFAULT_PREFERENCE
            val email = preferences[PreferenceKeys.email] ?: Constants.DEFAULT_PREFERENCE
            val birthDate = preferences[PreferenceKeys.birthDate] ?: Constants.DEFAULT_PREFERENCE
            val status = preferences[PreferenceKeys.status] ?: Constants.DEFAULT_PREFERENCE
            val idGender = preferences[PreferenceKeys.idGender] ?: IdGender.Male.idGender
            val allowSms = preferences[PreferenceKeys.allowSms] ?: true
            val allowEmail = preferences[PreferenceKeys.allowEmail] ?: true
            val allowPush = preferences[PreferenceKeys.allowPush] ?: true
            val emailVerified = preferences[PreferenceKeys.emailVerified] ?: true
            return@map UserInfoPref(
                id = id,
                phone = phone,
                name = name,
                email = email,
                birthDate = birthDate,
                status = status,
                idGender = idGender,
                allowSms = allowSms,
                allowEmail = allowEmail,
                allowPush = allowPush,
                emailVerified = emailVerified
            )
        }

    override suspend fun saveToken(token: String) {
        dataStore.edit { mutablePreferences ->
            mutablePreferences[PreferenceKeys.token] = token
        }
    }

    override fun readToken(): Flow<String> = dataStore.data
        .catch {
            emit(emptyPreferences())
        }.map { preferences ->
            return@map preferences[PreferenceKeys.token] ?: Constants.DEFAULT_PREFERENCE
        }

    override suspend fun saveRefresh(refresh: String) {
        dataStore.edit { mutablePreferences ->
            mutablePreferences[PreferenceKeys.refresh] = refresh
        }
    }

    override fun readRefresh(): Flow<String> = dataStore.data
        .catch {
            emit(emptyPreferences())
        }.map { preferences ->
            return@map preferences[PreferenceKeys.refresh] ?: Constants.DEFAULT_PREFERENCE
        }

    override suspend fun saveEmptyUserInfo() {
        dataStore.edit { mutablePreferences ->
            mutablePreferences[PreferenceKeys.id] = Constants.DEFAULT_PREFERENCE
            mutablePreferences[PreferenceKeys.phone] = Constants.DEFAULT_PREFERENCE
            mutablePreferences[PreferenceKeys.name] = Constants.DEFAULT_PREFERENCE
            mutablePreferences[PreferenceKeys.email] = Constants.DEFAULT_PREFERENCE
            mutablePreferences[PreferenceKeys.birthDate] = Constants.DEFAULT_PREFERENCE
            mutablePreferences[PreferenceKeys.status] = Constants.DEFAULT_PREFERENCE
            mutablePreferences[PreferenceKeys.idGender] = IdGender.Male.idGender
            mutablePreferences[PreferenceKeys.allowSms] = true
            mutablePreferences[PreferenceKeys.allowEmail] = true
            mutablePreferences[PreferenceKeys.allowPush] = true
            mutablePreferences[PreferenceKeys.emailVerified] = true
            mutablePreferences[PreferenceKeys.token] = Constants.DEFAULT_PREFERENCE
            mutablePreferences[PreferenceKeys.refresh] = Constants.DEFAULT_PREFERENCE
        }
    }

}