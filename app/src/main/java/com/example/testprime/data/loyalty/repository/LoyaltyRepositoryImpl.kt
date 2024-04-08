package com.example.testprime.data.loyalty.repository

import com.example.testprime.data.loyalty.datasource.LoyaltyDataSource
import com.example.testprime.data.loyalty.mapper.LoyaltyMapper
import com.example.testprime.domain.loyalty.repository.LoyaltyRepository
import com.example.testprime.data.common.network.SafeApiRequest
import com.example.testprime.domain.loyalty.model.Banner
import com.example.testprime.domain.loyalty.model.EstimateBody
import com.example.testprime.domain.loyalty.model.EstimateProduct
import com.example.testprime.domain.loyalty.model.InfoBirthDay
import com.example.testprime.domain.loyalty.model.PersonalOffer
import com.example.testprime.domain.loyalty.model.PopularProduct
import javax.inject.Inject

class LoyaltyRepositoryImpl @Inject constructor(
    private val loyaltyDataSource: LoyaltyDataSource,
    private val mapper: LoyaltyMapper
): LoyaltyRepository, SafeApiRequest() {

    override suspend fun getInfoBirthDay(token: String): InfoBirthDay {
        val response = safeApiRequest {
            loyaltyDataSource.getInfoBirthDay(token = token)
        }
        return mapper.mapInfoBirthDayDtoToEntity(response)
    }

    override suspend fun enableBirthDaySales(token: String): Boolean {
        return successApiRequest { loyaltyDataSource.enableBirthDaySales(token = token)}
    }

    override suspend fun getEstimateProducts(token: String, limit: Int): List<EstimateProduct> {
        val response = safeApiRequest {
            loyaltyDataSource.getEstimateProducts(token = token, limit = limit)
        }
        return mapper.mapEstimateProductsDtoToEntity(response)
    }

    override suspend fun postEstimate(token: String, idProduct: String, estimateBody: EstimateBody): Boolean {
        return successApiRequest { loyaltyDataSource.postEstimate(
            token = token,
            idProduct = idProduct,
            estimateBodyDto = mapper.mapEstimateBodyEntityToDto(estimateBody = estimateBody)
        )}
    }

    override suspend fun getBanners(token: String): List<Banner> {
        val response = safeApiRequest {
            loyaltyDataSource.getBanners(token = token)
        }
        return mapper.mapBannersDtoToEntity(response)
    }

    override suspend fun getPopularProducts(token: String): List<PopularProduct> {
        val response = safeApiRequest {
            loyaltyDataSource.getPopularProducts(token = token)
        }
        return mapper.mapPopularProductsDtoToEntity(response)
    }

    override suspend fun getPersonalOffers(token: String): List<PersonalOffer> {
        val response = safeApiRequest {
            loyaltyDataSource.getPersonalOffers(token = token)
        }
        return mapper.mapPersonalOffersDtoToEntity(response)
    }

    override suspend fun activatePersonalOffer(token: String, idPersonalOffer: String): Boolean {
        return successApiRequest {
            loyaltyDataSource.activatePersonalOffer(token = token, idPersonalOffer = idPersonalOffer)
        }
    }
}