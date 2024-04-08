package com.example.testprime.presentation.loyalty.estimate_product.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dot.prime.domain.orders.usecases.GetEstimateOrderUseCase
import com.example.testprime.presentation.loyalty.estimate_product.view.EstimateProductFragment.Companion.DEFAULT_ORDER
import com.example.testprime.common.Constants.Companion.MAX_PAGE_LIMIT
import com.example.testprime.common.UiState
import com.example.testprime.domain.loyalty.model.EstimateBody
import com.example.testprime.domain.loyalty.model.EstimateProduct
import com.example.testprime.domain.loyalty.usecases.GetEstimateProductsUseCase
import com.example.testprime.domain.loyalty.usecases.PostEstimateUseCase
import com.example.testprime.domain.profile.usecases.RefreshTokenUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EstimateProductViewModel @Inject constructor(
    private val getEstimateProductsUseCase: GetEstimateProductsUseCase,
    private val postEstimateUseCase: PostEstimateUseCase,
    private val getEstimateOrderUseCase: GetEstimateOrderUseCase,
    private val refreshTokenUseCase: RefreshTokenUseCase
): ViewModel() {

    private val _estimateProducts = MutableLiveData<UiState<List<EstimateProduct>>>(UiState.Default())
    val estimateProducts : LiveData<UiState<List<EstimateProduct>>> = _estimateProducts

    // For blocking list, when estimate loading
    private val _isBlockedList = MutableLiveData(false)
    val isBlockedList : LiveData<Boolean> = _isBlockedList

    fun getEstimateProducts(orderId: String){
        _estimateProducts.value = UiState.Loading()
        viewModelScope.launch {
            try {
                val estimateProductsList = if (orderId == DEFAULT_ORDER){
                    refreshTokenUseCase(request = { token ->
                        getEstimateProductsUseCase(token = token, limit = MAX_PAGE_LIMIT)
                    })
                }else{
                    refreshTokenUseCase(request = { token ->
                        getEstimateOrderUseCase(token = token, limit = MAX_PAGE_LIMIT, idOrder = orderId)
                    })
                }
                _estimateProducts.value = UiState.Success(data = estimateProductsList)
            }catch (exception: Exception){
                if (exception !is CancellationException){
                    _estimateProducts.value = UiState.Error(message = ERROR_GET_ESTIMATE_PRODUCT)
                }
            }
        }
    }

    fun postEstimateProduct(idProduct: String, grade: Int, userComment: String){
        viewModelScope.launch {
            _isBlockedList.value = true
            try {
                refreshTokenUseCase(request = {token ->
                    postEstimateUseCase(
                        token = token,
                        idProduct = idProduct,
                        estimateBody = EstimateBody(grade = grade, userComment = userComment)
                    )
                })
                val updatedList = _estimateProducts.value?.data?.toMutableList()
                updatedList?.removeIf { estimateProduct ->
                    estimateProduct.id == idProduct
                }
                _estimateProducts.value = UiState.Success(data = updatedList)
            }catch (exception: Exception){
                if (exception !is CancellationException){
                    _estimateProducts.value = UiState.Error(message = ERROR_POST_ESTIMATE_PRODUCT)
                }
            }
            _isBlockedList.value = false
        }
    }

    companion object{
        const val ERROR_POST_ESTIMATE_PRODUCT = "post_estimate_product_error"
        const val ERROR_GET_ESTIMATE_PRODUCT  = "get_estimate_product_error"
    }

}