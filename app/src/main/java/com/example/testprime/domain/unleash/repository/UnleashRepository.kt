package com.example.testprime.domain.unleash.repository

import com.example.testprime.domain.unleash.model.UnleashEntity
import kotlinx.coroutines.flow.Flow

interface UnleashRepository {

    suspend fun getAndSaveFeaturesUnleash()

    fun readFeaturesUnleash(): Flow<UnleashEntity>

}