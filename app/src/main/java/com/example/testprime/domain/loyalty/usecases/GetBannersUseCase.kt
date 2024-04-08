package com.example.testprime.domain.loyalty.usecases

import com.example.testprime.domain.loyalty.model.Banner
import com.example.testprime.domain.loyalty.repository.LoyaltyRepository
import javax.inject.Inject

class GetBannersUseCase @Inject constructor(private val repository: LoyaltyRepository) {

    suspend operator fun invoke(token: String): List<Banner>{
        return repository.getBanners(token = token)
    }

}