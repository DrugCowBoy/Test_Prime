package com.example.testprime.domain.profile.usecases

import android.util.Log
import com.example.testprime.common.Exceptions
import com.example.testprime.domain.profile.repository.ProfileRepository
import kotlinx.coroutines.flow.first
import javax.inject.Inject

class RefreshTokenUseCase @Inject constructor(
    private val repository: ProfileRepository,
    private val readTokenUseCase: ReadTokenUseCase,
    private val saveTokenUseCase: SaveTokenUseCase,
    private val readRefreshUseCase: ReadRefreshUseCase,
    private val saveRefreshUseCase: SaveRefreshUseCase
) {

    suspend operator fun <T> invoke(request: suspend (token:String) -> T): T{
        return try {
            val tokenOld = readTokenUseCase().first()
            val response = request.invoke(tokenOld)
            response
        }catch (exception: Exceptions.UnauthorizedException){
            refreshToken(request = request)
        }
    }

    private suspend fun <T> refreshToken(request: suspend (token:String) -> T): T{
        Log.i("Log_prime", "Refresh Token")
        val refreshOld = readRefreshUseCase().first()
        val refreshResponse = repository.refreshToken(refresh = refreshOld)
        saveTokenAndRefresh(token = refreshResponse.access!!, refresh = refreshResponse.refresh!!)
        val tokenNew = readTokenUseCase().first()
        return request.invoke(tokenNew)
    }

    private suspend fun saveTokenAndRefresh(token: String, refresh: String){
        saveTokenUseCase(token = token)
        saveRefreshUseCase(refresh = refresh)
    }

}