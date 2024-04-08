package com.example.testprime.domain.loyalty.repository

import com.example.testprime.domain.loyalty.model.Banner
import com.example.testprime.domain.loyalty.model.EstimateBody
import com.example.testprime.domain.loyalty.model.EstimateProduct
import com.example.testprime.domain.loyalty.model.InfoBirthDay
import com.example.testprime.domain.loyalty.model.PersonalOffer
import com.example.testprime.domain.loyalty.model.PopularProduct

interface LoyaltyRepository {

    suspend fun getInfoBirthDay(token: String): InfoBirthDay

    suspend fun enableBirthDaySales(token: String): Boolean

    suspend fun getEstimateProducts(token: String, limit: Int): List<EstimateProduct>

    suspend fun postEstimate(token: String, idProduct: String, estimateBody: EstimateBody): Boolean

    suspend fun getBanners(token: String): List<Banner>

    suspend fun getPopularProducts(token: String): List<PopularProduct>

    suspend fun getPersonalOffers(token: String): List<PersonalOffer>

    suspend fun activatePersonalOffer(token: String, idPersonalOffer: String): Boolean

}