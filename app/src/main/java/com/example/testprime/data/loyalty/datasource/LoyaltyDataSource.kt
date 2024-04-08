package com.example.testprime.data.loyalty.datasource

import com.example.testprime.data.loyalty.model.EstimateBodyDto
import com.example.testprime.data.loyalty.model.InfoBirthDayDto
import com.example.testprime.data.loyalty.model.ResultBannersDto
import com.example.testprime.data.loyalty.model.ResultEstimateProductsDto
import com.example.testprime.data.loyalty.model.ResultPersonalOffersDto
import com.example.testprime.data.loyalty.model.ResultPopularProductsDto
import okhttp3.ResponseBody
import retrofit2.Response

interface LoyaltyDataSource {

    suspend fun getInfoBirthDay(token: String): Response<InfoBirthDayDto>

    suspend fun enableBirthDaySales(token: String): Response<ResponseBody>

    suspend fun getEstimateProducts(token: String, limit: Int): Response<ResultEstimateProductsDto>

    suspend fun postEstimate(token: String, idProduct: String, estimateBodyDto: EstimateBodyDto): Response<ResponseBody>

    suspend fun getBanners(token: String): Response<ResultBannersDto>

    suspend fun getPopularProducts(token: String): Response<ResultPopularProductsDto>

    suspend fun getPersonalOffers(token: String): Response<ResultPersonalOffersDto>

    suspend fun activatePersonalOffer(token: String, idPersonalOffer: String): Response<ResponseBody>

}