package com.example.testprime.di

import com.example.testprime.data.basket.datasource.BasketDataSource
import com.example.testprime.data.basket.datasource.BasketDataSourceImpl
import com.dot.prime.data.favorites.datasource.FavoritesDataSource
import com.dot.prime.data.favorites.datasource.FavoritesDataSourceImpl
import com.dot.prime.data.hello.datasource.HelloDataSource
import com.dot.prime.data.hello.datasource.HelloDataSourceImpl
import com.example.testprime.data.loyalty.datasource.LoyaltyDataSource
import com.example.testprime.data.loyalty.datasource.LoyaltyDataSourceImpl
import com.example.testprime.data.menu.datasource.MenuDataSource
import com.example.testprime.data.menu.datasource.MenuDataSourceImpl
import com.dot.prime.data.orders.datasource.OrderDataSource
import com.dot.prime.data.orders.datasource.OrderDataSourceImpl
import com.dot.prime.data.our_cafes.datasource.OurCafesDataSource
import com.dot.prime.data.our_cafes.datasource.OurCafesDataSourceImpl
import com.dot.prime.data.preferred_groups.datasource.PreferredGroupsDataSource
import com.dot.prime.data.preferred_groups.datasource.PreferredGroupsDataSourceImpl
import com.example.testprime.data.product.datasource.ProductDataSource
import com.example.testprime.data.product.datasource.ProductDataSourceImpl
import com.example.testprime.data.profile.datasource.ProfileDataSource
import com.example.testprime.data.profile.datasource.ProfileDataSourceImpl
import com.dot.prime.data.settings_app.datasource.SettingsAppDataSource
import com.dot.prime.data.settings_app.datasource.SettingsAppDataSourceImpl
import com.dot.prime.data.status.datasource.StatusDataSource
import com.dot.prime.data.status.datasource.StatusDataSourceImpl
import com.dot.prime.data.telegram_bot.datasource.TelegramBotDataSource
import com.dot.prime.data.telegram_bot.datasource.TelegramBotDataSourceImpl
import com.example.testprime.data.unleash.datasource.UnleashDataSource
import com.example.testprime.data.unleash.datasource.UnleashDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class DataModule {

    @Binds
    abstract fun bindHelloDataSource(impl: HelloDataSourceImpl): HelloDataSource

    @Binds
    abstract fun bindProfileDataSource(impl: ProfileDataSourceImpl): ProfileDataSource

    @Binds
    abstract fun bindUnleashDataSource(impl: UnleashDataSourceImpl): UnleashDataSource

    @Binds
    abstract fun bindMenuDataSource(impl: MenuDataSourceImpl): MenuDataSource

    @Binds
    abstract fun bindProductDataSource(impl: ProductDataSourceImpl): ProductDataSource

    @Binds
    abstract fun bindBasketDataSource(impl: BasketDataSourceImpl): BasketDataSource

    @Binds
    abstract fun bindFavoritesDataSource(impl: FavoritesDataSourceImpl): FavoritesDataSource

    @Binds
    abstract fun bindLoyaltyDataSource(impl: LoyaltyDataSourceImpl): LoyaltyDataSource

    @Binds
    abstract fun bindPreferredGroupsDataSource(impl: PreferredGroupsDataSourceImpl): PreferredGroupsDataSource

    @Binds
    abstract fun bindOurCafesDataSource(impl: OurCafesDataSourceImpl): OurCafesDataSource
    @Binds
    abstract fun bindOrderDataSource(impl: OrderDataSourceImpl): OrderDataSource
    @Binds
    abstract fun bindStatusDataSource(impl: StatusDataSourceImpl): StatusDataSource

    @Binds
    abstract fun bindSettingsAppDataSource(impl: SettingsAppDataSourceImpl): SettingsAppDataSource

    @Binds
    abstract fun bindTelegramBotDataSource(impl: TelegramBotDataSourceImpl): TelegramBotDataSource



}