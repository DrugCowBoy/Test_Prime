package com.example.testprime.domain.profile.usecases

import com.example.testprime.domain.profile.repository.ProfileRepository
import javax.inject.Inject

class SaveRefreshUseCase @Inject constructor(private val repository: ProfileRepository) {

    suspend operator fun invoke(refresh: String){
        repository.saveRefresh(refresh)
    }

}