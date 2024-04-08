package com.example.testprime.presentation.loyalty.loyalty.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dot.prime.common.Constants.Companion.MIN_PAGE_LIMIT
import com.dot.prime.common.DevProd.Companion.IS_BOT_ENABLED
import com.dot.prime.common.UiState
import com.dot.prime.domain.loyalty.model.*
import com.dot.prime.domain.loyalty.usecases.*
import com.dot.prime.domain.preferred_groups.usecases.GetPreferredGroupsInfoUseCase
import com.dot.prime.domain.preferred_groups.usecases.GetStatusPreferredGroupsUseCase
import com.dot.prime.domain.profile.usecases.GetUserDataUseCase
import com.dot.prime.domain.profile.usecases.ReadUserInfoUseCase
import com.dot.prime.domain.profile.usecases.RefreshTokenUseCase
import com.dot.prime.domain.settings_app.model.RegisterFirebaseBody
import com.dot.prime.domain.settings_app.usecases.ReadIsFcmTokenUpdatedUseCase
import com.dot.prime.domain.settings_app.usecases.RegisterFirebaseUseCase
import com.dot.prime.domain.settings_app.usecases.SaveIsFcmTokenUpdatedUseCase
import com.dot.prime.domain.telegram_bot.usecases.BotSendMessageUseCase
import com.dot.prime.presentation.common.helper_class.LoyaltyInfo
import com.example.testprime.common.Constants.Companion.MIN_PAGE_LIMIT
import com.example.testprime.common.UiState
import com.example.testprime.domain.loyalty.model.PopularProduct
import com.example.testprime.domain.loyalty.usecases.EnableBirthDaySalesUseCase
import com.example.testprime.domain.loyalty.usecases.GetEstimateProductsUseCase
import com.example.testprime.domain.loyalty.usecases.GetInfoBirthDayUseCase
import com.example.testprime.domain.loyalty.usecases.GetPersonalOffersUseCase
import com.example.testprime.domain.loyalty.usecases.GetPopularProductsUseCase
import com.example.testprime.domain.profile.usecases.GetUserDataUseCase
import com.example.testprime.domain.profile.usecases.ReadUserInfoUseCase
import com.example.testprime.domain.profile.usecases.RefreshTokenUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.first
import javax.inject.Inject

@HiltViewModel
class LoyaltyViewModel @Inject constructor(
    private val getUserDataUseCase: GetUserDataUseCase,
    private val getInfoBirthDayUseCase: GetInfoBirthDayUseCase,
    private val getEstimateProductsUseCase: GetEstimateProductsUseCase,
    private val getBannersUseCase: GetBannersUseCase,
    private val getStatusPreferredGroupsUseCase: GetStatusPreferredGroupsUseCase,
    private val getPreferredGroupsInfoUseCase: GetPreferredGroupsInfoUseCase,
    private val getPopularProductsUseCase: GetPopularProductsUseCase,
    private val enableBirthDaySalesUseCase: EnableBirthDaySalesUseCase,
    private val getPersonalOffersUseCase: GetPersonalOffersUseCase,
    private val registerFirebaseUseCase: RegisterFirebaseUseCase,
    private val saveIsFcmTokenUpdatedUseCase: SaveIsFcmTokenUpdatedUseCase,
    private val readIsFcmTokenUpdatedUseCase: ReadIsFcmTokenUpdatedUseCase,
    private val readUserInfoUseCase: ReadUserInfoUseCase,
    private val refreshTokenUseCase: RefreshTokenUseCase
): ViewModel() {

    private var jobUserData: Job? = null

    var isPermissionShown = false

    private val _userData = MutableLiveData<UiState<LoyaltyInfo>>(UiState.Default())
    val userData : LiveData<UiState<LoyaltyInfo>> = _userData

    private val _popularProducts = MutableLiveData<UiState<List<PopularProduct>>>(UiState.Default())
    val popularProducts : LiveData<UiState<List<PopularProduct>>> = _popularProducts

    // For blocking screen
    private val _isBlockedScreen = MutableLiveData(false)
    val isBlockedScreen : LiveData<Boolean> = _isBlockedScreen

    private val exceptionHandlerAsyncQueries = CoroutineExceptionHandler { _, exception ->
        if (exception !is CancellationException){
            _userData.value = UiState.Error(message = ERROR_GET_LOYALTY)
        }
    }

    fun getUserData(){
        _userData.value = UiState.Loading()
        jobUserData = viewModelScope.launch(SupervisorJob() + exceptionHandlerAsyncQueries) {
            val userData = refreshTokenUseCase(request = { token ->
                getUserDataUseCase(token = token)
            })
            val countEstimateProducts = async {
                val estimateProductsList = try{
                    refreshTokenUseCase(request = { token ->
                        getEstimateProductsUseCase(token = token, MIN_PAGE_LIMIT)
                    })
                }catch (e: Exception){ emptyList()}
                if (estimateProductsList.isEmpty()) 0 else estimateProductsList[0].generalCount ?: 0
            }
            val infoBirthDay = async {
                refreshTokenUseCase(request = { token ->
                    getInfoBirthDayUseCase(token = token)
                })
            }
            val banners = async {
                refreshTokenUseCase(request = { token ->
                    getBannersUseCase(token = token)
                })
            }
            val statusPreferredGroups = async {
                refreshTokenUseCase(request = { token ->
                    getStatusPreferredGroupsUseCase(token = token)
                })
            }
            val infoPreferredGroups = async {
                refreshTokenUseCase(request = { token ->
                    getPreferredGroupsInfoUseCase(token = token)
                })
            }
            val personalDiscounts = async{
                refreshTokenUseCase{ token ->
                    getPersonalOffersUseCase(token = token)
                }
            }
            _userData.value = UiState.Success(
                data = LoyaltyInfo(
                    infoBirthDay = infoBirthDay.await(),
                    userData = userData,
                    countEstimateProducts = countEstimateProducts.await(),
                    banners = banners.await(),
                    statusPreferredGroups = statusPreferredGroups.await(),
                    infoPreferredGroups = infoPreferredGroups.await(),
                    personalDiscounts = personalDiscounts.await()
                )
            )
            getPopularProducts()
        }
    }

    private fun getPopularProducts(){
        jobUserData = viewModelScope.launch {
            try{
                if (_popularProducts.value !is UiState.Success){
                    _popularProducts.value = UiState.Loading()
                    val listPopularProducts = refreshTokenUseCase(request = { token ->
                        getPopularProductsUseCase(token = token)
                    })
                    _popularProducts.value = UiState.Success(data = listPopularProducts)
                }
            }catch (exception: Exception){
                if (exception !is CancellationException){
                    _popularProducts.value = UiState.Error(message = ERROR_GET_LOYALTY)
                }
            }
        }
    }

    fun enableBirthDaySales(){
        viewModelScope.launch {
            _isBlockedScreen.value = true
            try {
                // Enable sale
                refreshTokenUseCase(request = {token ->
                    enableBirthDaySalesUseCase(token = token)
                })
                try {
                    // Get new info about user
                    val userData = refreshTokenUseCase(request = { token ->
                        getUserDataUseCase(token = token)
                    })
                    val infoBirthDay = refreshTokenUseCase(request = { token ->
                        getInfoBirthDayUseCase(token = token)
                    })
                    _userData.value = UiState.Success(
                        data = LoyaltyInfo(
                            infoBirthDay = infoBirthDay,
                            userData = userData,
                            countEstimateProducts = _userData.value?.data?.countEstimateProducts,
                            banners = _userData.value?.data?.banners,
                            statusPreferredGroups = _userData.value?.data?.statusPreferredGroups,
                            infoPreferredGroups = _userData.value?.data?.infoPreferredGroups,
                            personalDiscounts = _userData.value?.data?.personalDiscounts
                        )
                    )
                }catch (exception: Exception){
                    if (exception !is CancellationException){
                        _userData.value = UiState.Error(message = ERROR_GET_LOYALTY)
                    }
                }
            }catch (exception: Exception){
                if (exception !is CancellationException){
                    _userData.value = UiState.Error(message = ERROR_POST_LOYALTY)
                }
            }
            _isBlockedScreen.value = false
        }
    }

    fun registerFirebase(deviceId: String, fcmToken: String){
        viewModelScope.launch {
            try{
                // Check we updated token or not
                val isFcmTokenUpdated = readIsFcmTokenUpdatedUseCase().first()
                if (!isFcmTokenUpdated){
                    // Register token
                    refreshTokenUseCase(request = {token ->
                        registerFirebaseUseCase(token = token, body = RegisterFirebaseBody(deviceId = deviceId, deviceToken = fcmToken))
                    })
                    // Save that we updated token
                    saveIsFcmTokenUpdatedUseCase(isFcmTokenUpdated = true)
                    Log.i("Log_prime", "Register Firebase Token: $fcmToken, Device: $deviceId")
                }
            }catch (exception: Exception){
                if (exception !is CancellationException){
                    Log.i("Log_prime", "Register Firebase Error")
                }
            }
        }
    }

    fun cancelQueries(){
        jobUserData?.cancel()
    }

    companion object{
        const val ERROR_POST_LOYALTY = "post_loyalty_error"
        const val ERROR_GET_LOYALTY  = "get_loyalty_error"
    }

}