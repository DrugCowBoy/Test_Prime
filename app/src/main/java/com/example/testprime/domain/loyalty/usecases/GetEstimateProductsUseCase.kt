package com.example.testprime.domain.loyalty.usecases

import com.example.testprime.domain.loyalty.model.EstimateProduct
import com.example.testprime.domain.loyalty.repository.LoyaltyRepository
import javax.inject.Inject

class GetEstimateProductsUseCase @Inject constructor(private val repository: LoyaltyRepository) {

    suspend operator fun invoke(token: String, limit: Int): List<EstimateProduct> {
        return repository.getEstimateProducts(token = token, limit = limit)
    }

}