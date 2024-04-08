package com.example.testprime.domain.profile.usecases

import com.example.testprime.domain.profile.repository.ProfileRepository
import javax.inject.Inject

class ChangeEmailUseCase @Inject constructor(private val repository: ProfileRepository) {

    suspend operator fun invoke(token: String, email: String): String{
        return repository.changeEmail(token = token, email = email)
    }

}