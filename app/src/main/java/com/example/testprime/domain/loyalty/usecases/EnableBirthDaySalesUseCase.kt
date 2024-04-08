package com.example.testprime.domain.loyalty.usecases

import com.example.testprime.domain.loyalty.repository.LoyaltyRepository
import javax.inject.Inject

class EnableBirthDaySalesUseCase @Inject constructor(private val repository: LoyaltyRepository) {

    suspend operator fun invoke(token: String): Boolean {
        return repository.enableBirthDaySales(token = token)
    }

}