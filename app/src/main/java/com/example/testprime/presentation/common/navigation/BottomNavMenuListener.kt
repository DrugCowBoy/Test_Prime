package com.dot.prime.presentation.common.navigation

interface BottomNavMenuListener {

    fun getCurrentBottomMenuTab(): BottomNavMenuTab

    fun setBottomMenuTab(tab: BottomNavMenuTab)

}