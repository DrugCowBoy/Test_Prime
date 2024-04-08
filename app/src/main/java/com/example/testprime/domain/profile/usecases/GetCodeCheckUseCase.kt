package com.example.testprime.domain.profile.usecases

import com.example.testprime.domain.profile.model.UserLoginResult
import com.example.testprime.domain.profile.repository.ProfileRepository
import javax.inject.Inject

class GetCodeCheckUseCase @Inject constructor(private val repository: ProfileRepository) {

    suspend operator fun invoke(token: String, code: String): UserLoginResult {
        return repository.getCodeCheck(token = token, code = code)
    }

}