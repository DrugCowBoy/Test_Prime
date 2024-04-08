package com.dot.prime.data.unleash.mapper

import com.example.testprime.data.unleash.model.UnleashDto
import com.example.testprime.data.unleash.model.UnleashPref
import com.example.testprime.domain.unleash.model.UnleashEntity
import com.example.testprime.common.DevProd.Companion.UNLEASH_ENVIRONMENT
import com.example.testprime.common.UnleashHelper
import javax.inject.Inject

class UnleashMapper @Inject constructor() {

    fun mapUnleashDtoToPref(unleashDto: UnleashDto): UnleashPref {
        val unleashPref = UnleashPref()
        unleashDto.features?.let{listFeatures ->
            for (feature in listFeatures){
                feature?.environments?.let{listEnvironment ->
                    for (environment in listEnvironment){
                        if (environment?.name == UNLEASH_ENVIRONMENT){
                            val name = feature.name
                            val enabled = environment.enabled
                            when(name){
                                UnleashHelper.FEATURE_MY_PRIME_STATUS -> unleashPref.myPrimeStatusIsEnabled = enabled
                                UnleashHelper.FEATURE_BASKET_MY_ORDER -> unleashPref.basketEnableMyOrder = enabled
                                UnleashHelper.FEATURE_TEST -> unleashPref.test = enabled
                                UnleashHelper.FEATURE_MY_PRIME_POINTS -> unleashPref.myPrimeSpendPointsIsEnabled = enabled
                                UnleashHelper.FEATURE_CHECK_PHONE -> unleashPref.userCheckByPhoneNumber = enabled

                                UnleashHelper.FEATURE_MY_PRIME_SUPPORT_CELL -> unleashPref.myPrimeSupportCelIsEnabled = enabled
                                UnleashHelper.FEATURE_PROFILE_INFO -> unleashPref.profileMyDataUsersProfileInfo = enabled
                                UnleashHelper.FEATURE_POPULAR_DISHES -> unleashPref.myPrimePopularDishesIsEnabled = enabled
                                UnleashHelper.FEATURE_TOP_BANNER -> unleashPref.menuTopBannerIsEnabled = enabled
                                UnleashHelper.FEATURE_DELIVERY_ADDRESS -> unleashPref.profileMyDataOrdersDeliveryAddress = enabled

                                UnleashHelper.FEATURE_MY_PRIME_USER_INFO -> unleashPref.myPrimeUserInfoIsEnabled = enabled
                                UnleashHelper.FEATURE_MY_PRIME_MAIN -> unleashPref.enableMyPrime = enabled
                                UnleashHelper.FEATURE_BASKET_MAIN -> unleashPref.enableBasketMain = enabled
                                UnleashHelper.FEATURE_FAVOURITE_MAIN -> unleashPref.enableFavoriteMain = enabled
                                UnleashHelper.FEATURE_PROFILE_MAIN -> unleashPref.enableProfileMain = enabled

                                UnleashHelper.FEATURE_MY_PRIME_TOP_BANNER -> unleashPref.myPrimeTopBannerIsEnabled = enabled
                                UnleashHelper.FEATURE_MY_PRIME_BAR_CODE -> unleashPref.myPrimeBarCodeIsEnabled = enabled
                                UnleashHelper.FEATURE_MY_PRIME_LOYALTY -> unleashPref.myPrimeLoyaltyIsEnabled = enabled
                                UnleashHelper.FEATURE_MY_PRIME_LOYALTY_CELL -> unleashPref.myPrimeLittleLoyaltyCellIsEnabled = enabled
                                UnleashHelper.FEATURE_MY_PRIME_PROMOTION -> unleashPref.myPrimePromotionIsEnabled = enabled

                                UnleashHelper.FEATURE_FOOD_MENU -> unleashPref.menuFoodMenuIsEnabled = enabled
                                UnleashHelper.FEATURE_MY_PRIME_FAVOURITE -> unleashPref.myPrimeFavoriteIsEnabled = enabled
                                UnleashHelper.FEATURE_PROFILE_MY_DATA -> unleashPref.profileMyDataMailingListsAndPromos = enabled
                                UnleashHelper.FEATURE_PROFILE_ORDERS -> unleashPref.profileOrdersMyOrders = enabled
                                UnleashHelper.FEATURE_PROFILE_ANSWERS -> unleashPref.profileSupportAnswerByQuestions = enabled

                                UnleashHelper.FEATURE_MENU_MAIN -> unleashPref.enableMainMenu = enabled
                                UnleashHelper.FEATURE_AUTH_SKIP -> unleashPref.authSkipIsEnabled = enabled
                                UnleashHelper.FEATURE_PROFILE_FIND_ANSWER -> unleashPref.profileFindTheAnswer = enabled
                                UnleashHelper.FEATURE_FAVOURITE_FOOD -> unleashPref.favoriteFavoriteFoodIsEnabled = enabled
                                UnleashHelper.CATALOG_PAGINATION -> unleashPref.catalogPaginationIsEnabled = enabled
                            }
                        }
                    }
                }
            }
        }
        return unleashPref
    }

    fun mapUnleashPrefToEntity(unleashPref: UnleashPref) = UnleashEntity(
        myPrimeStatusIsEnabled = unleashPref.myPrimeStatusIsEnabled ?: false,
        basketEnableMyOrder = unleashPref.basketEnableMyOrder ?: false,
        test = unleashPref.test ?: false,
        myPrimeSpendPointsIsEnabled = unleashPref.myPrimeSpendPointsIsEnabled ?: false,
        userCheckByPhoneNumber = unleashPref.userCheckByPhoneNumber ?: false,

        myPrimeSupportCelIsEnabled = unleashPref.myPrimeSupportCelIsEnabled ?: false,
        profileMyDataUsersProfileInfo = unleashPref.profileMyDataUsersProfileInfo ?: false,
        myPrimePopularDishesIsEnabled = unleashPref.myPrimePopularDishesIsEnabled ?: false,
        menuTopBannerIsEnabled = unleashPref.menuTopBannerIsEnabled ?: false,
        profileMyDataOrdersDeliveryAddress = unleashPref.profileMyDataOrdersDeliveryAddress ?: false,

        myPrimeUserInfoIsEnabled = unleashPref.myPrimeUserInfoIsEnabled ?: false,
        enableMyPrime = unleashPref.enableMyPrime ?: false,
        enableBasketMain = unleashPref.enableBasketMain ?: false,
        enableFavoriteMain = unleashPref.enableFavoriteMain ?: false,
        enableProfileMain = unleashPref.enableProfileMain ?: false,

        myPrimeTopBannerIsEnabled = unleashPref.myPrimeTopBannerIsEnabled ?: false,
        myPrimeBarCodeIsEnabled = unleashPref.myPrimeBarCodeIsEnabled ?: false,
        myPrimeLoyaltyIsEnabled = unleashPref.myPrimeLoyaltyIsEnabled ?: false,
        myPrimeLittleLoyaltyCellIsEnabled = unleashPref.myPrimeLittleLoyaltyCellIsEnabled ?: false,
        myPrimePromotionIsEnabled = unleashPref.myPrimePromotionIsEnabled ?: false,

        menuFoodMenuIsEnabled = unleashPref.menuFoodMenuIsEnabled ?: false,
        myPrimeFavoriteIsEnabled = unleashPref.myPrimeFavoriteIsEnabled ?: false,
        profileMyDataMailingListsAndPromos = unleashPref.profileMyDataMailingListsAndPromos ?: false,
        profileOrdersMyOrders = unleashPref.profileOrdersMyOrders ?: false,
        profileSupportAnswerByQuestions = unleashPref.profileSupportAnswerByQuestions ?: false,

        enableMainMenu = unleashPref.enableMainMenu ?: false,
        authSkipIsEnabled = unleashPref.authSkipIsEnabled ?: false,
        profileFindTheAnswer = unleashPref.profileFindTheAnswer ?: false,
        favoriteFavoriteFoodIsEnabled = unleashPref.favoriteFavoriteFoodIsEnabled ?: false,
        catalogPaginationIsEnabled = unleashPref.catalogPaginationIsEnabled ?: false
    )

}