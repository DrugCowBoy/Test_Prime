package com.example.testprime.di

import com.example.testprime.data.basket.repository.BasketRepositoryImpl
import com.dot.prime.data.favorites.repository.FavoritesRepositoryImpl
import com.dot.prime.data.hello.repository.HelloRepositoryImpl
import com.example.testprime.data.loyalty.repository.LoyaltyRepositoryImpl
import com.example.testprime.data.menu.repository.MenuRepositoryImpl
import com.dot.prime.data.our_cafes.repository.OurCafesRepositoryImpl
import com.dot.prime.data.preferred_groups.repository.PreferredGroupsRepositoryImpl
import com.example.testprime.data.product.repository.ProductRepositoryImpl
import com.dot.prime.data.orders.repository.OrderRepositoryImpl
import com.dot.prime.data.profile.repository.ProfileRepositoryImpl
import com.dot.prime.data.settings_app.repository.SettingsAppRepositoryImpl
import com.dot.prime.data.telegram_bot.repository.TelegramBotRepositoryImpl
import com.dot.prime.data.status.repository.StatusRepositoryImpl
import com.example.testprime.data.unleash.repository.UnleashRepositoryImpl
import com.example.testprime.domain.basket.repository.BasketRepository
import com.dot.prime.domain.favorites.repository.FavoritesRepository
import com.dot.prime.domain.hello.repository.HelloRepository
import com.example.testprime.domain.loyalty.repository.LoyaltyRepository
import com.example.testprime.domain.menu.repository.MenuRepository
import com.dot.prime.domain.our_cafes.repository.OurCafesRepository
import com.dot.prime.domain.preferred_groups.repository.PreferredGroupsRepository
import com.example.testprime.domain.product.repository.ProductRepository
import com.dot.prime.domain.orders.repository.OrderRepository
import com.example.testprime.domain.profile.repository.ProfileRepository
import com.dot.prime.domain.settings_app.repository.SettingsAppRepository
import com.dot.prime.domain.telegram_bot.repository.TelegramBotRepository
import com.dot.prime.domain.status.repository.StatusRepository
import com.example.testprime.domain.unleash.repository.UnleashRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class DomainModule {

    @Binds
    abstract fun bindHelloRepository(impl: HelloRepositoryImpl): HelloRepository

    @Binds
    abstract fun bindProfileRepository(impl: ProfileRepositoryImpl): ProfileRepository

    @Binds
    abstract fun bindUnleashRepository(impl: UnleashRepositoryImpl): UnleashRepository

    @Binds
    abstract fun bindMenuRepository(impl: MenuRepositoryImpl): MenuRepository

    @Binds
    abstract fun bindProductRepository(impl: ProductRepositoryImpl): ProductRepository

    @Binds
    abstract fun bindBasketRepository(impl: BasketRepositoryImpl): BasketRepository

    @Binds
    abstract fun bindFavoritesRepository(impl: FavoritesRepositoryImpl): FavoritesRepository

    @Binds
    abstract fun bindLoyaltyRepository(impl: LoyaltyRepositoryImpl): LoyaltyRepository

    @Binds
    abstract fun bindPreferredGroupsRepository(impl: PreferredGroupsRepositoryImpl): PreferredGroupsRepository

    @Binds
    abstract fun bindOurCafesRepository(impl: OurCafesRepositoryImpl): OurCafesRepository

    @Binds
    abstract fun bindSettingsAppRepository(impl: SettingsAppRepositoryImpl): SettingsAppRepository

    @Binds
    abstract fun bindOrderRepository(impl: OrderRepositoryImpl) : OrderRepository
    @Binds
    abstract fun bindStatusRepository(impl: StatusRepositoryImpl) : StatusRepository

    @Binds
    abstract fun bindTelegramBotRepository(impl: TelegramBotRepositoryImpl): TelegramBotRepository

}