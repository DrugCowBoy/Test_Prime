package com.example.testprime.presentation.basket.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.testprime.common.UiState
import com.example.testprime.domain.basket.model.AddAndDecreaseBasketBody
import com.example.testprime.domain.basket.model.ItemBasket
import com.example.testprime.domain.basket.usecases.ChangeItemBasketUseCase
import com.example.testprime.domain.basket.usecases.ClearAllBasketUseCase
import com.example.testprime.domain.basket.usecases.GetBasketProductsUseCase
import com.example.testprime.domain.profile.usecases.RefreshTokenUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BasketViewModel @Inject constructor(
    private val getBasketProductsUseCase: GetBasketProductsUseCase,
    private val changeItemBasketUseCase: ChangeItemBasketUseCase,
    private val clearAllBasketUseCase: ClearAllBasketUseCase,
    private val refreshTokenUseCase: RefreshTokenUseCase
): ViewModel() {

    private var jobBasket: Job? = null

    private val _basketProducts = MutableLiveData<UiState<List<ItemBasket>>>(UiState.Default())
    val basketProducts : LiveData<UiState<List<ItemBasket>>> = _basketProducts

    // For blocking list, when product loading in basket
    private val _isBlockedList = MutableLiveData(false)
    val isBlockedList : LiveData<Boolean> = _isBlockedList

    fun getBasketProducts(){
        _basketProducts.value = UiState.Loading()
        jobBasket = viewModelScope.launch {
            try{
                val listBasket = refreshTokenUseCase(request = { token ->
                    getBasketProductsUseCase(token = token)
                })
                _basketProducts.value = UiState.Success(data = sortListBasket(listBasket))
            }catch (exception: Exception){
                if (exception !is CancellationException){
                    _basketProducts.value = UiState.Error(message = ERROR_GET_BASKET)
                }
            }
        }
    }

    fun changeBasket(id: String, quantity: Int){
        jobBasket = viewModelScope.launch {
            _isBlockedList.value = true
            try{
                val listBasket = refreshTokenUseCase(request = {token ->
                    changeItemBasketUseCase(
                        token = token,
                        basketBody = AddAndDecreaseBasketBody(productId = id, quantity = quantity)
                    )
                })
                _basketProducts.value = UiState.Success(data = sortListBasket(listBasket))
            }catch (exception: Exception){
                if (exception !is CancellationException){
                    _basketProducts.value = UiState.Error(message = ERROR_POST_BASKET)
                }
            }
            _isBlockedList.value = false
        }
    }

    fun clearAllBasket(){
        _basketProducts.value = UiState.Loading()
        jobBasket = viewModelScope.launch {
            try{
                val listBasket = refreshTokenUseCase(request = { token ->
                    clearAllBasketUseCase(token = token)
                })
                _basketProducts.value = UiState.Success(data = sortListBasket(listBasket))
            }catch (exception: Exception){
                if (exception !is CancellationException){
                    _basketProducts.value = UiState.Error(message = ERROR_GET_BASKET)
                }
            }
        }
    }

    fun cancelQueries(){
        jobBasket?.cancel()
    }

    // Sort list by name to show static recyclerView
    private fun sortListBasket(listBasket: List<ItemBasket>): List<ItemBasket>{
        val sortedList = listBasket.sortedBy { itemBasket ->
            itemBasket.product?.id
        }
        return sortedList
    }

    companion object{
        const val ERROR_POST_BASKET = "post_basket_error"
        const val ERROR_GET_BASKET  = "get_basket_error"
    }
}