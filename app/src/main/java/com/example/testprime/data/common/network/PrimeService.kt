package com.example.testprime.data.common.network

import com.dot.prime.common.Constants.Companion.MAX_PAGE_LIMIT
import com.example.testprime.common.enum_classes.PeriodMonth
import com.example.testprime.data.basket.model.AddAndDecreaseBasketBodyDto
import com.example.testprime.data.basket.model.DeleteBasketBodyDto
import com.example.testprime.data.basket.model.ResultBasketProductsDto
import com.dot.prime.data.favorites.model.FavoritesBodyDto
import com.dot.prime.data.favorites.model.ResultFavoriteProductsDto
import com.dot.prime.data.hello.model.HelloDto
import com.dot.prime.data.loyalty.model.*
import com.example.testprime.data.profile.model.FullUserDataDto
import com.dot.prime.data.menu.model.ResultCategoriesProductDto
import com.dot.prime.data.menu.model.ResultProductsDto
import com.dot.prime.data.orders.model.EstimateVisitBodyDto
import com.dot.prime.data.orders.model.ResultFullOrderDto
import com.dot.prime.data.orders.model.ResultShortOrdersDto
import com.dot.prime.data.our_cafes.model.ResultOrganizationsDto
import com.dot.prime.data.preferred_groups.model.PreferredGroupsBodyDto
import com.dot.prime.data.preferred_groups.model.ResultInfoPreferredGroupsDto
import com.dot.prime.data.preferred_groups.model.ResultPreferredGroupsDto
import com.dot.prime.data.preferred_groups.model.ResultStatusPreferredGroupsDto
import com.example.testprime.data.product.model.ProductItemDto
import com.dot.prime.data.profile.model.*
import com.dot.prime.data.settings_app.model.RegisterFirebaseBodyDto
import com.dot.prime.data.settings_app.model.ResultVersionAppDto
import com.dot.prime.data.settings_app.model.VersionAppBodyDto
import com.dot.prime.data.status.model.StatusResultDto
import com.example.testprime.data.loyalty.model.EstimateBodyDto
import com.example.testprime.data.loyalty.model.InfoBirthDayDto
import com.example.testprime.data.loyalty.model.ResultBannersDto
import com.example.testprime.data.loyalty.model.ResultEstimateProductsDto
import com.example.testprime.data.loyalty.model.ResultPersonalOffersDto
import com.example.testprime.data.loyalty.model.ResultPopularProductsDto
import com.example.testprime.data.profile.model.AccessAndRefreshDto
import com.example.testprime.data.profile.model.ChangeEmailBodyDto
import com.example.testprime.data.profile.model.CodeBodyDto
import com.example.testprime.data.profile.model.DataChangeTokenDto
import com.example.testprime.data.profile.model.PhoneBodyDto
import com.example.testprime.data.profile.model.PhoneTokenDto
import com.example.testprime.data.profile.model.RefreshBodyDto
import com.example.testprime.data.profile.model.UserBodyDto
import com.example.testprime.data.profile.model.UserDataDto
import com.example.testprime.data.profile.model.UserLoginDto
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.*

interface PrimeService {

    @GET("hello")
    suspend fun getHello(): Response<HelloDto>

    @POST("user_phone_check")
    suspend fun getPhoneCheck(
        @Body phoneBodyDto: PhoneBodyDto
    ): Response<PhoneTokenDto>

    @POST("phone_login")
    suspend fun getCodeCheck(
        @Body codeBodyDto: CodeBodyDto
    ): Response<UserLoginDto>

    @POST("refresh_token")
    suspend fun refreshToken(
        @Body refreshBodyDto: RefreshBodyDto
    ): Response<UserLoginDto>

    @PUT("users/me")
    suspend fun putUserData(
        @Header("Authorization") token: String,
        @Body userBodyDto: UserBodyDto
    ): Response<UserDataDto>

    @PUT("users/me/update_by_manager")
    suspend fun managerPutUserData(
        @Header("Authorization") token: String,
        @Body userBodyDto: UserBodyDto
    ): Response<UserDataDto>

    @GET("users/me")
    suspend fun getUserData(
        @Header("Authorization") token: String
    ): Response<FullUserDataDto>

    @DELETE("users/me")
    suspend fun deleteUserData(
        @Header("Authorization") token: String
    ): Response<ResponseBody>

    @GET("categories")
    suspend fun getCategoriesProduct(
        @Header("Authorization") token: String
    ): Response<ResultCategoriesProductDto>

    @GET("products")
    suspend fun getProducts(
        @Header("Authorization") token: String,
        @Query("page") page: Int = 1,
        @Query("limit") limit: Int = MAX_PAGE_LIMIT,
        @Query("q") search: String
    ): Response<ResultProductsDto>

    @GET("products")
    suspend fun getProductsPaging(
        @Header("Authorization") token: String,
        @Query("page") page: Int,
        @Query("limit") limit: Int,
        @Query("q") search: String,
        @Query("category_id") categoryId: String?
    ): Response<ResultProductsDto>

    @GET("products/{id}")
    suspend fun getProductItem(
        @Header("Authorization") token: String,
        @Path("id") idProduct: String
    ): Response<ProductItemDto>

    @GET("basket")
    suspend fun getBasketProducts(
        @Header("Authorization") token: String
    ): Response<ResultBasketProductsDto>

    @POST("basket/products")
    suspend fun addAndDecreaseBasket(
        @Header("Authorization") token: String,
        @Body basketBodyDto: AddAndDecreaseBasketBodyDto
    ): Response<ResultBasketProductsDto>

    @HTTP(method = "DELETE", path = "basket/products", hasBody = true)
    suspend fun deleteBasket(
        @Header("Authorization") token: String,
        @Body basketBodyDto: DeleteBasketBodyDto
    ): Response<ResultBasketProductsDto>

    @GET("favorites")
    suspend fun getFavoriteProducts(
        @Header("Authorization") token: String
    ): Response<ResultFavoriteProductsDto>

    @DELETE("basket")
    suspend fun clearAllBasket(
        @Header("Authorization") token: String
    ): Response<ResultBasketProductsDto>

    @POST("favorites")
    suspend fun addFavorites(
        @Header("Authorization") token: String,
        @Body favoritesBodyDto: FavoritesBodyDto
    ): Response<ResponseBody>

    @HTTP(method = "DELETE", path = "favorites", hasBody = true)
    suspend fun deleteFavorites(
        @Header("Authorization") token: String,
        @Body favoritesBodyDto: FavoritesBodyDto
    ): Response<ResponseBody>

    @GET("loyalty/get_birth_day_sales_info")
    suspend fun getInfoBirthDay(
        @Header("Authorization") token: String
    ): Response<InfoBirthDayDto>

    @PUT("users/me/enable_birth_day_sales")
    suspend fun enableBirthDaySales(
        @Header("Authorization") token: String
    ): Response<ResponseBody>

    @GET("orders/rating")
    suspend fun getEstimateProducts(
        @Header("Authorization") token: String,
        @Query("page") page: Int = 1,
        @Query("limit") limit: Int
    ): Response<ResultEstimateProductsDto>

    @POST("orders/grades/{id}")
    suspend fun postEstimate(
        @Header("Authorization") token: String,
        @Path("id") idProduct: String,
        @Body estimateBodyDto: EstimateBodyDto
    ): Response<ResponseBody>

    @GET("banners")
    suspend fun getBanners(
        @Header("Authorization") token: String
    ): Response<ResultBannersDto>

    @GET("preferred_groups/by_period")
    suspend fun getAllPreferredGroups(
        @Header("Authorization") token: String,
        @Query("page") page: Int = 1,
        @Query("limit") limit: Int = MAX_PAGE_LIMIT,
        @Query("period") period: Int
    ): Response<ResultPreferredGroupsDto>

    @GET("preferred_groups/status")
    suspend fun getStatusPreferredGroups(
        @Header("Authorization") token: String
    ): Response<ResultStatusPreferredGroupsDto>

    @POST("preferred_groups")
    suspend fun postPreferredGroups(
        @Header("Authorization") token: String,
        @Body preferredGroupsBodyDto: PreferredGroupsBodyDto
    ): Response<ResponseBody>

    @GET("preferred_groups/checked")
    suspend fun getCheckedPreferredGroups(
        @Header("Authorization") token: String,
        @Query("month") monthNumber: Int
    ): Response<ResultPreferredGroupsDto>

    @GET("preferred_groups/info")
    suspend fun getPreferredGroupsInfo(
        @Header("Authorization") token: String,
        @Query("period") period: Int = PeriodMonth.CurrentMonth.period
    ): Response<ResultInfoPreferredGroupsDto>

    @GET("products/populars")
    suspend fun getPopularProducts(
        @Header("Authorization") token: String,
        @Query("page") page: Int = 1,
        @Query("limit") limit: Int = 10
    ): Response<ResultPopularProductsDto>

    @GET("organizations")
    suspend fun getOrganizations(
        @Header("Authorization") token: String
    ): Response<ResultOrganizationsDto>

    @GET("orders")
    suspend fun getOrders(
        @Header("Authorization") token: String
    ): Response<ResultShortOrdersDto>

    @GET("orders/{id}/items")
    suspend fun getOrderInfoById(
        @Header("Authorization") token: String,
        @Path("id") id: String
    ): Response<ResultFullOrderDto>

    @POST("version")
    suspend fun checkVersionApp(
        @Body versionAppBodyDto: VersionAppBodyDto
    ): Response<ResultVersionAppDto>

    @POST("push")
    suspend fun registerFirebase(
        @Header("Authorization") token: String,
        @Body registerFirebaseBodyDto: RegisterFirebaseBodyDto
    ): Response<ResponseBody>

    @POST("users/me/user_update_phone_request")
    suspend fun changeUserPhone(
        @Header("Authorization") token: String,
        @Body phoneBodyDto: PhoneBodyDto
    ): Response<DataChangeTokenDto>

    @PATCH("users/me/user_update_phone_confirm")
    suspend fun changeUserPhoneCode(
        @Header("Authorization") token: String,
        @Body codeBodyDto: CodeBodyDto
    ): Response<AccessAndRefreshDto>

    @GET("users/me/status")
    suspend fun getStatuses(
        @Header("Authorization") token: String
    ): Response<StatusResultDto>

    @POST("users/me/send_email_verification_link")
    suspend fun verifyEmail(
        @Header("Authorization") token: String
    ): Response<DataChangeTokenDto>

    @POST("users/me/user_update_email_request")
    suspend fun changeEmail(
        @Header("Authorization") token: String,
        @Body changeEmailBodyDto: ChangeEmailBodyDto
    ): Response<DataChangeTokenDto>

    @GET("personal_offers")
    suspend fun getPersonalOffers(
        @Header("Authorization") token: String
    ): Response<ResultPersonalOffersDto>

    @PATCH("personal_offers/{id}")
    suspend fun activatePersonalOffer(
        @Header("Authorization") token: String,
        @Path("id") idPersonalOffer: String
    ): Response<ResponseBody>

    @GET("orders/rating/{id}")
    suspend fun getEstimateOrder(
        @Header("Authorization") token: String,
        @Path("id") idOrder: String,
        @Query("page") page: Int = 1,
        @Query("limit") limit: Int
    ): Response<ResultEstimateProductsDto>

    @POST("orders/visit_grades/{id}")
    suspend fun postEstimateVisit(
        @Header("Authorization") token: String,
        @Path("id") idOrder: String,
        @Body estimateVisitBodyDto: EstimateVisitBodyDto
    ): Response<ResponseBody>

}