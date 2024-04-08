package com.example.testprime.presentation.loyalty.date_birth_can_activate.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dot.prime.common.UiState
import com.dot.prime.domain.hello.usecases.GetHelloUseCase
import com.dot.prime.domain.loyalty.usecases.EnableBirthDaySalesUseCase
import com.dot.prime.domain.profile.usecases.RefreshTokenUseCase
import com.dot.prime.domain.profile.usecases.TestRefreshTokenUseCase
import com.example.testprime.domain.loyalty.usecases.EnableBirthDaySalesUseCase
import com.example.testprime.domain.profile.usecases.RefreshTokenUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DateBirthCanActivateViewModel @Inject constructor(
    private val enableBirthDaySalesUseCase: EnableBirthDaySalesUseCase,
    private val refreshTokenUseCase: RefreshTokenUseCase
): ViewModel() {

    private val _birthDaySales = MutableLiveData<UiState<Boolean>>(UiState.Default())
    val birthDaySales : LiveData<UiState<Boolean>> = _birthDaySales

    fun enableBirthDaySales(){
        _birthDaySales.value = UiState.Loading()
        viewModelScope.launch {
            try {
                refreshTokenUseCase(request = {token ->
                    enableBirthDaySalesUseCase(token = token)
                })
                _birthDaySales.value = UiState.Success(data = true)
            }catch (exception: Exception){
                _birthDaySales.value = UiState.Error(message = exception.message)
            }
        }
    }

}