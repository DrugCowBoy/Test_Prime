package com.example.testprime.domain.profile.usecases

import com.example.testprime.domain.profile.model.FullUserDataResult
import com.example.testprime.domain.profile.repository.ProfileRepository
import javax.inject.Inject

class GetUserDataUseCase @Inject constructor(private val repository: ProfileRepository) {

    suspend operator fun invoke(token: String): FullUserDataResult {
        return repository.getUserData(token = token)
    }

}