package com.example.testprime.data.unleash.model

data class UnleashPref(
    var myPrimeStatusIsEnabled: Boolean? = false,
    var basketEnableMyOrder: Boolean? = false,
    var test: Boolean? = false,
    var myPrimeSpendPointsIsEnabled: Boolean? = false,
    var userCheckByPhoneNumber: Boolean? = false,

    var myPrimeSupportCelIsEnabled: Boolean? = false,
    var profileMyDataUsersProfileInfo: Boolean? = false,
    var myPrimePopularDishesIsEnabled: Boolean? = false,
    var menuTopBannerIsEnabled: Boolean? = false,
    var profileMyDataOrdersDeliveryAddress: Boolean? = false,

    var myPrimeUserInfoIsEnabled: Boolean? = false,
    var enableMyPrime: Boolean? = false,
    var enableBasketMain: Boolean? = false,
    var enableFavoriteMain: Boolean? = false,
    var enableProfileMain: Boolean? = false,

    var myPrimeTopBannerIsEnabled: Boolean? = false,
    var myPrimeBarCodeIsEnabled: Boolean? = false,
    var myPrimeLoyaltyIsEnabled: Boolean? = false,
    var myPrimeLittleLoyaltyCellIsEnabled: Boolean? = false,
    var myPrimePromotionIsEnabled: Boolean? = false,

    var menuFoodMenuIsEnabled: Boolean? = false,
    var myPrimeFavoriteIsEnabled: Boolean? = false,
    var profileMyDataMailingListsAndPromos: Boolean? = false,
    var profileOrdersMyOrders: Boolean? = false,
    var profileSupportAnswerByQuestions: Boolean? = false,

    var enableMainMenu: Boolean? = false,
    var authSkipIsEnabled: Boolean? = false,
    var profileFindTheAnswer: Boolean? = false,
    var favoriteFavoriteFoodIsEnabled: Boolean? = false,
    var catalogPaginationIsEnabled: Boolean? = false
)