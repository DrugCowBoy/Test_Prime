package com.example.testprime.domain.unleash.model

data class UnleashEntity (
    val myPrimeStatusIsEnabled: Boolean,
    val basketEnableMyOrder: Boolean,
    val test: Boolean,
    val myPrimeSpendPointsIsEnabled: Boolean,
    val userCheckByPhoneNumber: Boolean?,

    val myPrimeSupportCelIsEnabled: Boolean?,
    val profileMyDataUsersProfileInfo: Boolean?,
    val myPrimePopularDishesIsEnabled: Boolean?,
    val menuTopBannerIsEnabled: Boolean?,
    val profileMyDataOrdersDeliveryAddress: Boolean?,

    val myPrimeUserInfoIsEnabled: Boolean?,
    val enableMyPrime: Boolean?,
    val enableBasketMain: Boolean?,
    val enableFavoriteMain: Boolean?,
    val enableProfileMain: Boolean?,

    val myPrimeTopBannerIsEnabled: Boolean?,
    val myPrimeBarCodeIsEnabled: Boolean?,
    val myPrimeLoyaltyIsEnabled: Boolean?,
    val myPrimeLittleLoyaltyCellIsEnabled: Boolean?,
    val myPrimePromotionIsEnabled: Boolean?,

    val menuFoodMenuIsEnabled: Boolean?,
    val myPrimeFavoriteIsEnabled: Boolean?,
    val profileMyDataMailingListsAndPromos: Boolean?,
    val profileOrdersMyOrders: Boolean?,
    val profileSupportAnswerByQuestions: Boolean?,

    val enableMainMenu: Boolean?,
    val authSkipIsEnabled: Boolean?,
    val profileFindTheAnswer: Boolean?,
    val favoriteFavoriteFoodIsEnabled: Boolean?,
    val catalogPaginationIsEnabled: Boolean?
)