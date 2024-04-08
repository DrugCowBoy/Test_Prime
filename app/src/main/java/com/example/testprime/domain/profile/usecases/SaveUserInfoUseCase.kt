package com.example.testprime.domain.profile.usecases

import com.example.testprime.domain.profile.model.UserInfo
import com.example.testprime.domain.profile.repository.ProfileRepository
import javax.inject.Inject

class SaveUserInfoUseCase @Inject constructor(private val repository: ProfileRepository) {

    suspend operator fun invoke(userInfo: UserInfo){
        repository.saveUserInfo(userInfo = userInfo)
    }
    
}