package com.example.testprime.data.loyalty.datasource

import com.example.testprime.data.common.network.PrimeService
import com.example.testprime.data.loyalty.model.EstimateBodyDto
import com.example.testprime.data.loyalty.model.InfoBirthDayDto
import com.example.testprime.data.loyalty.model.ResultBannersDto
import com.example.testprime.data.loyalty.model.ResultEstimateProductsDto
import com.example.testprime.data.loyalty.model.ResultPersonalOffersDto
import com.example.testprime.data.loyalty.model.ResultPopularProductsDto
import okhttp3.ResponseBody
import retrofit2.Response
import javax.inject.Inject

class LoyaltyDataSourceImpl @Inject constructor(
    private val primeService: PrimeService
): LoyaltyDataSource {

    override suspend fun getInfoBirthDay(token: String): Response<InfoBirthDayDto> {
        return primeService.getInfoBirthDay(token = token)
    }

    override suspend fun enableBirthDaySales(token: String): Response<ResponseBody> {
        return primeService.enableBirthDaySales(token = token)
    }

    override suspend fun getEstimateProducts(token: String, limit: Int): Response<ResultEstimateProductsDto> {
        return primeService.getEstimateProducts(token = token, limit = limit)
    }

    override suspend fun postEstimate(token: String, idProduct: String, estimateBodyDto: EstimateBodyDto): Response<ResponseBody> {
        return primeService.postEstimate(token = token, idProduct = idProduct, estimateBodyDto = estimateBodyDto)
    }

    override suspend fun getBanners(token: String): Response<ResultBannersDto> {
        return primeService.getBanners(token = token)
    }

    override suspend fun getPopularProducts(token: String): Response<ResultPopularProductsDto> {
        return primeService.getPopularProducts(token = token)
    }

    override suspend fun getPersonalOffers(token: String): Response<ResultPersonalOffersDto> {
        return primeService.getPersonalOffers(token = token)
    }

    override suspend fun activatePersonalOffer(
        token: String,
        idPersonalOffer: String
    ): Response<ResponseBody> {
        return primeService.activatePersonalOffer(
            token = token,
            idPersonalOffer = idPersonalOffer
        )
    }

}