package com.example.testprime.domain.profile.usecases

import com.example.testprime.domain.profile.repository.ProfileRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ReadTokenUseCase @Inject constructor(private val repository: ProfileRepository) {

    operator fun invoke(): Flow<String> {
        return repository.readToken()
    }

}