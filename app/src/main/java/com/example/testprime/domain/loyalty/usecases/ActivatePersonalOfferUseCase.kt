package com.example.testprime.domain.loyalty.usecases

import com.example.testprime.domain.loyalty.repository.LoyaltyRepository
import javax.inject.Inject

class ActivatePersonalOfferUseCase @Inject constructor(private val repository: LoyaltyRepository) {

    suspend operator fun invoke(token: String, idPersonalOffer: String): Boolean {
        return repository.activatePersonalOffer(token = token, idPersonalOffer = idPersonalOffer)
    }

}