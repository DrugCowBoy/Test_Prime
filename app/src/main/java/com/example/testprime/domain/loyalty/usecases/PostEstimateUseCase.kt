package com.example.testprime.domain.loyalty.usecases

import com.example.testprime.domain.loyalty.model.EstimateBody
import com.example.testprime.domain.loyalty.repository.LoyaltyRepository
import javax.inject.Inject

class PostEstimateUseCase @Inject constructor(private val repository: LoyaltyRepository) {

    suspend operator fun invoke(token: String, idProduct: String, estimateBody: EstimateBody): Boolean {
        return repository.postEstimate(token = token, idProduct = idProduct, estimateBody = estimateBody)
    }

}