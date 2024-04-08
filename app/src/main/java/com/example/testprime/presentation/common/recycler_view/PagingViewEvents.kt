package com.dot.prime.presentation.common.recycler_view

import com.dot.prime.domain.basket.model.ItemBasket

sealed class PagingViewEvents {
    data class Edit(val listBasket: List<ItemBasket>) : PagingViewEvents()
}