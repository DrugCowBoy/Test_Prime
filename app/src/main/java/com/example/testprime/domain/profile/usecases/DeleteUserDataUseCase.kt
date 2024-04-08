package com.example.testprime.domain.profile.usecases

import com.example.testprime.domain.profile.repository.ProfileRepository
import javax.inject.Inject

class DeleteUserDataUseCase @Inject constructor(private val repository: ProfileRepository) {

    suspend operator fun invoke(token: String): Boolean {
        return repository.deleteUserData(token = token)
    }

}