package com.example.testprime.domain.profile.usecases

import android.util.Log
import com.example.testprime.common.formatDate
import com.example.testprime.domain.profile.model.UserDataResult
import com.example.testprime.domain.profile.repository.ProfileRepository
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

class PutUserDataUseCase @Inject constructor(private val repository: ProfileRepository) {

    suspend operator fun invoke(
        token: String,
        name: String,
        email: String?,
        birthDate: String,
        idGender: Int,
        allowEmail: Boolean,
        allowSms: Boolean,
        allowPush: Boolean
    ): UserDataResult {
        return repository.putUserData(
            token = token,
            name = name,
            email = email?.trim(),
            birthDate = birthDate.formatDate(),
            idGender = idGender,
            allowEmail = allowEmail,
            allowSms = allowSms,
            allowPush = allowPush
        )
    }

}