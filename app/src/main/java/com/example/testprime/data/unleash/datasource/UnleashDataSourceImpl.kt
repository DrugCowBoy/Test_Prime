package com.example.testprime.data.unleash.datasource

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.*
import com.example.testprime.data.unleash.model.UnleashDto
import com.example.testprime.data.unleash.model.UnleashPref
import com.example.testprime.common.UnleashHelper
import com.example.testprime.data.common.network.UnleashService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import retrofit2.Response
import javax.inject.Inject

class UnleashDataSourceImpl @Inject constructor(
    private val unleashService: UnleashService,
    private val dataStore: DataStore<Preferences>
): UnleashDataSource {

    override suspend fun getFeaturesUnleash(): Response<UnleashDto> {
        return unleashService.getFeaturesUnleash()
    }

    private object PreferenceKeys{
        val myPrimeStatusIsEnabled = booleanPreferencesKey(UnleashHelper.FEATURE_MY_PRIME_STATUS)
        val basketEnableMyOrder = booleanPreferencesKey(UnleashHelper.FEATURE_BASKET_MY_ORDER )
        val test = booleanPreferencesKey(UnleashHelper.FEATURE_TEST)
        val myPrimeSpendPointsIsEnabled = booleanPreferencesKey(UnleashHelper.FEATURE_MY_PRIME_POINTS)
        val userCheckByPhoneNumber = booleanPreferencesKey(UnleashHelper.FEATURE_CHECK_PHONE)

        val myPrimeSupportCelIsEnabled = booleanPreferencesKey(UnleashHelper.FEATURE_MY_PRIME_SUPPORT_CELL)
        val profileMyDataUsersProfileInfo = booleanPreferencesKey(UnleashHelper.FEATURE_PROFILE_INFO)
        val myPrimePopularDishesIsEnabled = booleanPreferencesKey(UnleashHelper.FEATURE_POPULAR_DISHES)
        val menuTopBannerIsEnabled = booleanPreferencesKey(UnleashHelper.FEATURE_TOP_BANNER)
        val profileMyDataOrdersDeliveryAddress = booleanPreferencesKey(UnleashHelper.FEATURE_DELIVERY_ADDRESS)

        val myPrimeUserInfoIsEnabled = booleanPreferencesKey(UnleashHelper.FEATURE_MY_PRIME_USER_INFO)
        val enableMyPrime = booleanPreferencesKey(UnleashHelper.FEATURE_MY_PRIME_MAIN)
        val enableBasketMain = booleanPreferencesKey(UnleashHelper.FEATURE_BASKET_MAIN)
        val enableFavoriteMain = booleanPreferencesKey(UnleashHelper.FEATURE_FAVOURITE_MAIN)
        val enableProfileMain = booleanPreferencesKey(UnleashHelper.FEATURE_PROFILE_MAIN)

        val myPrimeTopBannerIsEnabled = booleanPreferencesKey(UnleashHelper.FEATURE_MY_PRIME_TOP_BANNER)
        val myPrimeBarCodeIsEnabled = booleanPreferencesKey(UnleashHelper.FEATURE_MY_PRIME_BAR_CODE)
        val myPrimeLoyaltyIsEnabled = booleanPreferencesKey(UnleashHelper.FEATURE_MY_PRIME_LOYALTY)
        val myPrimeLittleLoyaltyCellIsEnabled = booleanPreferencesKey(UnleashHelper.FEATURE_MY_PRIME_LOYALTY_CELL)
        val myPrimePromotionIsEnabled = booleanPreferencesKey(UnleashHelper.FEATURE_MY_PRIME_PROMOTION)

        val menuFoodMenuIsEnabled = booleanPreferencesKey(UnleashHelper.FEATURE_FOOD_MENU)
        val myPrimeFavoriteIsEnabled = booleanPreferencesKey(UnleashHelper.FEATURE_MY_PRIME_FAVOURITE)
        val profileMyDataMailingListsAndPromos = booleanPreferencesKey(UnleashHelper.FEATURE_PROFILE_MY_DATA)
        val profileOrdersMyOrders = booleanPreferencesKey(UnleashHelper.FEATURE_PROFILE_ORDERS)
        val profileSupportAnswerByQuestions = booleanPreferencesKey(UnleashHelper.FEATURE_PROFILE_ANSWERS)

        val enableMainMenu = booleanPreferencesKey(UnleashHelper.FEATURE_MENU_MAIN)
        val authSkipIsEnabled = booleanPreferencesKey(UnleashHelper.FEATURE_AUTH_SKIP)
        val profileFindTheAnswer = booleanPreferencesKey(UnleashHelper.FEATURE_PROFILE_FIND_ANSWER)
        val favoriteFavoriteFoodIsEnabled = booleanPreferencesKey(UnleashHelper.FEATURE_FAVOURITE_FOOD)
        val catalogPaginationIsEnabled = booleanPreferencesKey(UnleashHelper.CATALOG_PAGINATION)
    }

    override suspend fun saveFeaturesUnleash(unleashPref: UnleashPref) {
        dataStore.edit { mutablePreferences ->
            mutablePreferences[PreferenceKeys.myPrimeStatusIsEnabled] = unleashPref.myPrimeStatusIsEnabled ?: false
            mutablePreferences[PreferenceKeys.basketEnableMyOrder] = unleashPref.basketEnableMyOrder ?: false
            mutablePreferences[PreferenceKeys.test] = unleashPref.test ?: false
            mutablePreferences[PreferenceKeys.myPrimeSpendPointsIsEnabled] = unleashPref.myPrimeSpendPointsIsEnabled ?: false
            mutablePreferences[PreferenceKeys.userCheckByPhoneNumber] = unleashPref.userCheckByPhoneNumber ?: false

            mutablePreferences[PreferenceKeys.myPrimeSupportCelIsEnabled] = unleashPref.myPrimeSupportCelIsEnabled ?: false
            mutablePreferences[PreferenceKeys.profileMyDataUsersProfileInfo] = unleashPref.profileMyDataUsersProfileInfo ?: false
            mutablePreferences[PreferenceKeys.myPrimePopularDishesIsEnabled] = unleashPref.myPrimePopularDishesIsEnabled ?: false
            mutablePreferences[PreferenceKeys.menuTopBannerIsEnabled] = unleashPref.menuTopBannerIsEnabled ?: false
            mutablePreferences[PreferenceKeys.profileMyDataOrdersDeliveryAddress] = unleashPref.profileMyDataOrdersDeliveryAddress ?: false

            mutablePreferences[PreferenceKeys.myPrimeUserInfoIsEnabled] = unleashPref.myPrimeUserInfoIsEnabled ?: false
            mutablePreferences[PreferenceKeys.enableMyPrime] = unleashPref.enableMyPrime ?: false
            mutablePreferences[PreferenceKeys.enableBasketMain] = unleashPref.enableBasketMain ?: false
            mutablePreferences[PreferenceKeys.enableFavoriteMain] = unleashPref.enableFavoriteMain ?: false
            mutablePreferences[PreferenceKeys.enableProfileMain] = unleashPref.enableProfileMain ?: false

            mutablePreferences[PreferenceKeys.myPrimeTopBannerIsEnabled] = unleashPref.myPrimeTopBannerIsEnabled ?: false
            mutablePreferences[PreferenceKeys.myPrimeBarCodeIsEnabled] = unleashPref.myPrimeBarCodeIsEnabled ?: false
            mutablePreferences[PreferenceKeys.myPrimeLoyaltyIsEnabled] = unleashPref.myPrimeLoyaltyIsEnabled ?: false
            mutablePreferences[PreferenceKeys.myPrimeLittleLoyaltyCellIsEnabled] = unleashPref.myPrimeLittleLoyaltyCellIsEnabled ?: false
            mutablePreferences[PreferenceKeys.myPrimePromotionIsEnabled] = unleashPref.myPrimePromotionIsEnabled ?: false

            mutablePreferences[PreferenceKeys.menuFoodMenuIsEnabled] = unleashPref.menuFoodMenuIsEnabled ?: false
            mutablePreferences[PreferenceKeys.myPrimeFavoriteIsEnabled] = unleashPref.myPrimeFavoriteIsEnabled ?: false
            mutablePreferences[PreferenceKeys.profileMyDataMailingListsAndPromos] = unleashPref.profileMyDataMailingListsAndPromos ?: false
            mutablePreferences[PreferenceKeys.profileOrdersMyOrders] = unleashPref.profileOrdersMyOrders ?: false
            mutablePreferences[PreferenceKeys.profileSupportAnswerByQuestions] = unleashPref.profileSupportAnswerByQuestions ?: false

            mutablePreferences[PreferenceKeys.enableMainMenu] = unleashPref.enableMainMenu ?: false
            mutablePreferences[PreferenceKeys.authSkipIsEnabled] = unleashPref.authSkipIsEnabled ?: false
            mutablePreferences[PreferenceKeys.profileFindTheAnswer] = unleashPref.profileFindTheAnswer ?: false
            mutablePreferences[PreferenceKeys.favoriteFavoriteFoodIsEnabled] = unleashPref.favoriteFavoriteFoodIsEnabled ?: false
            mutablePreferences[PreferenceKeys.catalogPaginationIsEnabled] = unleashPref.catalogPaginationIsEnabled ?: false
        }
    }

    override fun readFeaturesUnleash(): Flow<UnleashPref> = dataStore.data
    .catch {
        emit(emptyPreferences())
    }.map { preferences ->
        val myPrimeStatusIsEnabled = preferences[PreferenceKeys.myPrimeStatusIsEnabled] ?: false
        val basketEnableMyOrder = preferences[PreferenceKeys.basketEnableMyOrder] ?: false
        val test = preferences[PreferenceKeys.test] ?: false
        val myPrimeSpendPointsIsEnabled = preferences[PreferenceKeys.myPrimeSpendPointsIsEnabled] ?: false
        val userCheckByPhoneNumber = preferences[PreferenceKeys.userCheckByPhoneNumber] ?: false

        val myPrimeSupportCelIsEnabled = preferences[PreferenceKeys.myPrimeSupportCelIsEnabled] ?: false
        val profileMyDataUsersProfileInfo = preferences[PreferenceKeys.profileMyDataUsersProfileInfo] ?: false
        val myPrimePopularDishesIsEnabled = preferences[PreferenceKeys.myPrimePopularDishesIsEnabled] ?: false
        val menuTopBannerIsEnabled = preferences[PreferenceKeys.menuTopBannerIsEnabled] ?: false
        val profileMyDataOrdersDeliveryAddress = preferences[PreferenceKeys.profileMyDataOrdersDeliveryAddress] ?: false

        val myPrimeUserInfoIsEnabled = preferences[PreferenceKeys.myPrimeUserInfoIsEnabled] ?: false
        val enableMyPrime = preferences[PreferenceKeys.enableMyPrime] ?: false
        val enableBasketMain = preferences[PreferenceKeys.enableBasketMain] ?: false
        val enableFavoriteMain = preferences[PreferenceKeys.enableFavoriteMain] ?: false
        val enableProfileMain = preferences[PreferenceKeys.enableProfileMain] ?: false

        val myPrimeTopBannerIsEnabled = preferences[PreferenceKeys.myPrimeTopBannerIsEnabled] ?: false
        val myPrimeBarCodeIsEnabled = preferences[PreferenceKeys.myPrimeBarCodeIsEnabled] ?: false
        val myPrimeLoyaltyIsEnabled = preferences[PreferenceKeys.myPrimeLoyaltyIsEnabled] ?: false
        val myPrimeLittleLoyaltyCellIsEnabled = preferences[PreferenceKeys.myPrimeLittleLoyaltyCellIsEnabled] ?: false
        val myPrimePromotionIsEnabled = preferences[PreferenceKeys.myPrimePromotionIsEnabled] ?: false

        val menuFoodMenuIsEnabled = preferences[PreferenceKeys.menuFoodMenuIsEnabled] ?: false
        val myPrimeFavoriteIsEnabled = preferences[PreferenceKeys.myPrimeFavoriteIsEnabled] ?: false
        val profileMyDataMailingListsAndPromos = preferences[PreferenceKeys.profileMyDataMailingListsAndPromos] ?: false
        val profileOrdersMyOrders = preferences[PreferenceKeys.profileOrdersMyOrders] ?: false
        val profileSupportAnswerByQuestions = preferences[PreferenceKeys.profileSupportAnswerByQuestions] ?: false

        val enableMainMenu = preferences[PreferenceKeys.enableMainMenu] ?: false
        val authSkipIsEnabled = preferences[PreferenceKeys.authSkipIsEnabled] ?: false
        val profileFindTheAnswer = preferences[PreferenceKeys.profileFindTheAnswer] ?: false
        val favoriteFavoriteFoodIsEnabled = preferences[PreferenceKeys.favoriteFavoriteFoodIsEnabled] ?: false
        val catalogPaginationIsEnabled = preferences[PreferenceKeys.catalogPaginationIsEnabled] ?: false

        return@map UnleashPref(
            myPrimeStatusIsEnabled = myPrimeStatusIsEnabled,
            basketEnableMyOrder = basketEnableMyOrder,
            test = test,
            myPrimeSpendPointsIsEnabled = myPrimeSpendPointsIsEnabled,
            userCheckByPhoneNumber = userCheckByPhoneNumber,

            myPrimeSupportCelIsEnabled = myPrimeSupportCelIsEnabled,
            profileMyDataUsersProfileInfo = profileMyDataUsersProfileInfo,
            myPrimePopularDishesIsEnabled = myPrimePopularDishesIsEnabled,
            menuTopBannerIsEnabled = menuTopBannerIsEnabled,
            profileMyDataOrdersDeliveryAddress = profileMyDataOrdersDeliveryAddress,

            myPrimeUserInfoIsEnabled = myPrimeUserInfoIsEnabled,
            enableMyPrime = enableMyPrime,
            enableBasketMain = enableBasketMain,
            enableFavoriteMain = enableFavoriteMain,
            enableProfileMain = enableProfileMain,

            myPrimeTopBannerIsEnabled = myPrimeTopBannerIsEnabled,
            myPrimeBarCodeIsEnabled = myPrimeBarCodeIsEnabled,
            myPrimeLoyaltyIsEnabled = myPrimeLoyaltyIsEnabled,
            myPrimeLittleLoyaltyCellIsEnabled = myPrimeLittleLoyaltyCellIsEnabled,
            myPrimePromotionIsEnabled = myPrimePromotionIsEnabled,

            menuFoodMenuIsEnabled = menuFoodMenuIsEnabled,
            myPrimeFavoriteIsEnabled = myPrimeFavoriteIsEnabled,
            profileMyDataMailingListsAndPromos = profileMyDataMailingListsAndPromos,
            profileOrdersMyOrders = profileOrdersMyOrders,
            profileSupportAnswerByQuestions = profileSupportAnswerByQuestions,

            enableMainMenu = enableMainMenu,
            authSkipIsEnabled = authSkipIsEnabled,
            profileFindTheAnswer = profileFindTheAnswer,
            favoriteFavoriteFoodIsEnabled = favoriteFavoriteFoodIsEnabled,
            catalogPaginationIsEnabled = catalogPaginationIsEnabled
        )
    }

}