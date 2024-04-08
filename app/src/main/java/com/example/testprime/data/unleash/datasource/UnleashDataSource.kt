package com.example.testprime.data.unleash.datasource

import com.example.testprime.data.unleash.model.UnleashDto
import com.example.testprime.data.unleash.model.UnleashPref
import retrofit2.Response
import kotlinx.coroutines.flow.Flow

interface UnleashDataSource {

    suspend fun getFeaturesUnleash(): Response<UnleashDto>

    suspend fun saveFeaturesUnleash(unleashPref: UnleashPref)

    fun readFeaturesUnleash(): Flow<UnleashPref>

}