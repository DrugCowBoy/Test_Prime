package com.example.testprime.data.loyalty.mapper

import com.example.testprime.data.loyalty.model.EstimateBodyDto
import com.example.testprime.data.loyalty.model.InfoBirthDayDto
import com.example.testprime.data.loyalty.model.PopularProductDto
import com.example.testprime.data.loyalty.model.ResultBannersDto
import com.example.testprime.data.loyalty.model.ResultEstimateProductsDto
import com.example.testprime.data.loyalty.model.ResultPersonalOffersDto
import com.example.testprime.data.loyalty.model.ResultPopularProductsDto
import com.example.testprime.domain.loyalty.model.Banner
import com.example.testprime.domain.loyalty.model.CategoryProduct
import com.example.testprime.domain.loyalty.model.EstimateBody
import com.example.testprime.domain.loyalty.model.EstimateProduct
import com.example.testprime.domain.loyalty.model.Image
import com.example.testprime.domain.loyalty.model.InfoBirthDay
import com.example.testprime.domain.loyalty.model.Options
import com.example.testprime.domain.loyalty.model.PersonalOffer
import com.example.testprime.domain.loyalty.model.PopularProduct
import javax.inject.Inject

class LoyaltyMapper @Inject constructor() {

    fun mapInfoBirthDayDtoToEntity(infoBirthDayDto: InfoBirthDayDto) = InfoBirthDay(
        longInfo = infoBirthDayDto.longInfo,
        shortInfo = infoBirthDayDto.shortInfo,
        sub = infoBirthDayDto.sub,
        title = infoBirthDayDto.title
    )

    fun mapEstimateProductsDtoToEntity(resultEstimateProductsDto: ResultEstimateProductsDto): List<EstimateProduct>{
        val generalCount = resultEstimateProductsDto.pagination?.total ?: 0
        val listEstimateProducts = resultEstimateProductsDto.result?.map { estimateProductsDto ->
            EstimateProduct(
                id = estimateProductsDto?.id,
                image = estimateProductsDto?.image,
                name = estimateProductsDto?.name,
                paymentDate = estimateProductsDto?.paymentDate?.replace("-", "."),
                generalCount = generalCount
            )
        }
        return listEstimateProducts ?: emptyList<EstimateProduct>()
    }

    fun mapEstimateBodyEntityToDto(estimateBody: EstimateBody) = EstimateBodyDto(
        grade = estimateBody.grade,
        userComment = estimateBody.userComment
    )

    fun mapBannersDtoToEntity(resultBannersDto: ResultBannersDto): List<Banner>{
        val listBanners = resultBannersDto.result?.map { bannerDto ->
            Banner(
                description = bannerDto?.description,
                target = bannerDto?.target,
                title = bannerDto?.title,
                url = bannerDto?.url
            )
        }
        return listBanners ?: emptyList<Banner>()
    }

    fun mapPopularProductsDtoToEntity(resultPopularProductsDto: ResultPopularProductsDto): List<PopularProduct>{
        val listPopularProductsDto = resultPopularProductsDto.result
        val listProducts = listPopularProductsDto?.map { popularProductDto ->
            mapPopularProductDtoToEntity(popularProductDto)
        }
        return listProducts ?: emptyList<PopularProduct>()
    }

    private fun mapPopularProductDtoToEntity(popularProductDto: PopularProductDto) = PopularProduct(
        category = CategoryProduct(
            description = popularProductDto.category?.description,
            id = popularProductDto.category?.id,
            mainImageLink = popularProductDto.category?.mainImageLink,
            name = popularProductDto.category?.name
        ),
        description = popularProductDto.description,
        id = popularProductDto.id,
        images = popularProductDto.images?.map { imageDto ->
            Image(
                link = imageDto?.link
            )
        },
        measure = popularProductDto.measure,
        name = popularProductDto.name,
        options = Options(
            carbohydrate = popularProductDto.options?.carbohydrate,
            carbohydratePerHundredGrams = popularProductDto.options?.carbohydratePerHundredGrams,
            energy = popularProductDto.options?.energy,
            energyPerHundredGrams = popularProductDto.options?.energyPerHundredGrams,
            fat = popularProductDto.options?.fat,
            fatPerHundredGrams = popularProductDto.options?.fatPerHundredGrams,
            protein = popularProductDto.options?.protein,
            proteinPerHundredGrams = popularProductDto.options?.proteinPerHundredGrams,
            weight = popularProductDto.options?.weight
        ),
        price = popularProductDto.price
    )

    fun mapPersonalOffersDtoToEntity(resultPersonalOffersDto: ResultPersonalOffersDto): List<PersonalOffer>{
        val listPersonalOffer = resultPersonalOffersDto.personalOffer?.map { personalOfferDto ->
            PersonalOffer(
                finishDateTime = personalOfferDto?.finishDateTime,
                id = personalOfferDto?.id,
                isActive = personalOfferDto?.isActive,
                maxQuantity = personalOfferDto?.maxQuantity,
                productDescription = personalOfferDto?.productDescription,
                productIconUrl = personalOfferDto?.productIconUrl,
                productName = personalOfferDto?.productName,
                startDateTime = personalOfferDto?.startDateTime,
                value = personalOfferDto?.value
            )
        }
        return listPersonalOffer ?: emptyList()
    }

}