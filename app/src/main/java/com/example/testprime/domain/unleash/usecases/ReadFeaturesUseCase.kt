package com.example.testprime.domain.unleash.usecases

import com.example.testprime.domain.unleash.model.UnleashEntity
import com.example.testprime.domain.unleash.repository.UnleashRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ReadFeaturesUseCase @Inject constructor(private val repository: UnleashRepository) {

    operator fun invoke(): Flow<UnleashEntity> {
        return repository.readFeaturesUnleash()
    }
}