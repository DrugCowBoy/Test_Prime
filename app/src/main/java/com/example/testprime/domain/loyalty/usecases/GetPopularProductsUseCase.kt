package com.example.testprime.domain.loyalty.usecases

import com.example.testprime.domain.loyalty.model.PopularProduct
import com.example.testprime.domain.loyalty.repository.LoyaltyRepository
import javax.inject.Inject

class GetPopularProductsUseCase @Inject constructor(private val repository: LoyaltyRepository) {

    suspend operator fun invoke(token: String): List<PopularProduct> {
        return repository.getPopularProducts(token = token)
    }

}