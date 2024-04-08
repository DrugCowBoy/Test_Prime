package com.example.testprime.domain.loyalty.usecases

import com.example.testprime.domain.loyalty.model.PersonalOffer
import com.example.testprime.domain.loyalty.repository.LoyaltyRepository
import javax.inject.Inject

class GetPersonalOffersUseCase @Inject constructor(private val repository: LoyaltyRepository) {

    suspend operator fun invoke(token: String): List<PersonalOffer> {
        return repository.getPersonalOffers(token = token)
    }

}