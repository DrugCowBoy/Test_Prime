package com.example.testprime.data.common.network

import android.util.Log
import com.example.testprime.common.Exceptions
import okhttp3.ResponseBody
import retrofit2.Response

// Return Object inside Response<> or throw Exception
abstract class SafeApiRequest {

    // Response with detection error code
    suspend fun <T> safeApiRequest(call: suspend() -> Response<T>): T{
        val response = call.invoke()// throw exception, if no connection
        if (response.isSuccessful){
            return response.body()!!// throw exception, if body is null
        }else{
            val codeError = response.code()
            val responseError = response.errorBody().toString()
            Log.i("Log_prime", "SafeApiRequest: $codeError, $responseError")
            when(codeError){
                401 -> throw Exceptions.UnauthorizedException()// throw exception, if user unauthorized
                422 -> throw Exceptions.DataAlreadyExistException()// throw exception, if data exist on server
                else -> throw Exception(codeError.toString())
            }
        }
    }

    // Response success or not
    suspend fun successApiRequest(call: suspend() -> Response<ResponseBody>): Boolean{
        val response = call.invoke()// throw exception, if no connection
        if (response.isSuccessful){
            return true
        }else{
            val codeError = response.code()
            val responseError = response.errorBody().toString()
            Log.i("Log_prime", "SuccessApiRequest: ${codeError.toString()}, $responseError")
            if (codeError == 401){
                throw Exceptions.UnauthorizedException()
            }else{
                throw Exception(responseError)
            }
        }
    }

}

