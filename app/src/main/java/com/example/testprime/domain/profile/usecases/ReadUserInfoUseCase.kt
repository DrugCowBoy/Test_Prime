package com.example.testprime.domain.profile.usecases

import com.example.testprime.domain.profile.model.UserInfo
import com.example.testprime.domain.profile.repository.ProfileRepository
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow

class ReadUserInfoUseCase @Inject constructor(private val repository: ProfileRepository) {

    operator fun invoke(): Flow<UserInfo>{
        return repository.readUserInfo()
    }

}