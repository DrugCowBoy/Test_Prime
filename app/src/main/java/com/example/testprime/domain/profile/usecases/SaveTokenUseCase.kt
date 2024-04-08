package com.example.testprime.domain.profile.usecases

import com.example.testprime.common.Constants.Companion.BEARER
import com.example.testprime.domain.profile.repository.ProfileRepository
import javax.inject.Inject

class SaveTokenUseCase @Inject constructor(private val repository: ProfileRepository) {

    suspend operator fun invoke(token: String){
        repository.saveToken(BEARER + token)
    }

}