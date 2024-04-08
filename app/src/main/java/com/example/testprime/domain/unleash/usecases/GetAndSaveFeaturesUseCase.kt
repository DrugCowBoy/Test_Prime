package com.example.testprime.domain.unleash.usecases

import com.example.testprime.domain.unleash.repository.UnleashRepository
import javax.inject.Inject

class GetAndSaveFeaturesUseCase @Inject constructor(private val repository: UnleashRepository) {

    suspend operator fun invoke(){
        repository.getAndSaveFeaturesUnleash()
    }

}