package com.example.testprime.domain.profile.usecases

import com.example.testprime.domain.profile.repository.ProfileRepository
import javax.inject.Inject

class SaveEmptyUserInfoUseCase @Inject constructor(private val repository: ProfileRepository) {

    suspend operator fun invoke(){
        repository.saveEmptyUserInfo()
    }

}