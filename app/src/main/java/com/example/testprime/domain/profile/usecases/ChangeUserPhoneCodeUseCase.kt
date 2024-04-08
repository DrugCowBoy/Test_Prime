package com.example.testprime.domain.profile.usecases

import com.example.testprime.domain.profile.model.AccessAndRefresh
import com.example.testprime.domain.profile.repository.ProfileRepository
import javax.inject.Inject

class ChangeUserPhoneCodeUseCase @Inject constructor(private val repository: ProfileRepository) {

    suspend operator fun invoke(token: String, tokenPhone: String, code: String): AccessAndRefresh {
        return repository.changeUserPhoneCode(
            token = token,
            tokenPhone = tokenPhone,
            code = code
        )
    }

}