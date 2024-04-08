package com.example.testprime.data.unleash.repository

import com.example.testprime.data.unleash.datasource.UnleashDataSource
import com.dot.prime.data.unleash.mapper.UnleashMapper
import com.example.testprime.data.unleash.model.UnleashPref
import com.example.testprime.domain.unleash.model.UnleashEntity
import com.example.testprime.domain.unleash.repository.UnleashRepository
import com.example.testprime.data.common.network.SafeApiRequest
import com.example.testprime.di.IoDispatcher
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class UnleashRepositoryImpl @Inject constructor(
    private val unleashDataSource: UnleashDataSource,
    private val mapper: UnleashMapper,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher
): UnleashRepository, SafeApiRequest() {

    override suspend fun getAndSaveFeaturesUnleash(){
        val response = safeApiRequest { unleashDataSource.getFeaturesUnleash()}
        val unleashPref = mapper.mapUnleashDtoToPref(response)
        saveFeaturesUnleash(unleashPref)
    }

    private suspend fun saveFeaturesUnleash(unleashPref: UnleashPref) {
        withContext(ioDispatcher){
            unleashDataSource.saveFeaturesUnleash(unleashPref)
        }
    }

    override fun readFeaturesUnleash(): Flow<UnleashEntity>{
        return unleashDataSource.readFeaturesUnleash().map { unleashPref ->
            mapper.mapUnleashPrefToEntity(unleashPref)
        }
    }

}