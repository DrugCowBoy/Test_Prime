package com.dot.prime.presentation.common.helper_class

import com.dot.prime.domain.orders.model.FullOrderItem

data class OrderEstimateInfo(
    val listOrderItem: List<FullOrderItem>,
    val countListOrderEstimate: Int
)