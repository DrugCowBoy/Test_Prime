package com.example.testprime.domain.profile.usecases

import com.example.testprime.domain.profile.repository.ProfileRepository
import javax.inject.Inject

class VerifyEmailUseCase @Inject constructor(private val repository: ProfileRepository) {

    suspend operator fun invoke(token: String): String{
        return repository.verifyEmail(token = token)
    }

}