package com.example.testprime.di

import com.example.testprime.data.common.network.PrimeService
import com.dot.prime.common.DevProd.Companion.BASE_URL_PRIME
import com.dot.prime.common.UnleashHelper.Companion.BASE_URL_UNLEASH
import com.example.testprime.data.common.network.UnleashService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Qualifier
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Singleton
    @Provides
    fun provideHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .readTimeout(15, TimeUnit.SECONDS)
            .connectTimeout(15, TimeUnit.SECONDS)
            .build()
    }

    @Singleton
    @Provides
    fun provideConverterFactory(): GsonConverterFactory {
        return GsonConverterFactory.create()
    }

    /** PRIME */
    @Singleton
    @Provides
    @Prime
    fun providePrimeRetrofit(
        okHttpClient: OkHttpClient,
        gsonConverterFactory: GsonConverterFactory
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL_PRIME)
            .client(okHttpClient)
            .addConverterFactory(gsonConverterFactory)
            .build()

    }

    @Singleton
    @Provides
    fun providePrimeService(@Prime retrofit: Retrofit): PrimeService {
        return retrofit.create(PrimeService::class.java)
    }

    /** UNLEASH */
    @Singleton
    @Provides
    @Unleash
    fun provideUnleashRetrofit(
        okHttpClient: OkHttpClient,
        gsonConverterFactory: GsonConverterFactory
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL_UNLEASH)
            .client(okHttpClient)
            .addConverterFactory(gsonConverterFactory)
            .build()

    }

    @Singleton
    @Provides
    fun provideUnleashService(@Unleash retrofit: Retrofit): UnleashService {
        return retrofit.create(UnleashService::class.java)
    }

}

@Retention
@Qualifier
annotation class Prime

@Retention
@Qualifier
annotation class Unleash