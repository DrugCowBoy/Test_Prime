package com.example.testprime.domain.loyalty.usecases

import com.example.testprime.domain.loyalty.model.InfoBirthDay
import com.example.testprime.domain.loyalty.repository.LoyaltyRepository
import javax.inject.Inject

class GetInfoBirthDayUseCase @Inject constructor(private val repository: LoyaltyRepository) {

    suspend operator fun invoke(token: String): InfoBirthDay {
        return repository.getInfoBirthDay(token = token)
    }

}