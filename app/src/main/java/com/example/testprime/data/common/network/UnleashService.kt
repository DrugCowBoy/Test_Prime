package com.example.testprime.data.common.network

import com.example.testprime.data.unleash.model.UnleashDto
import com.example.testprime.common.UnleashHelper.Companion.TOKEN_UNLEASH
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header

interface UnleashService {

    @GET("features")
    suspend fun getFeaturesUnleash(
        @Header("Authorization") token: String = TOKEN_UNLEASH
    ): Response<UnleashDto>

}