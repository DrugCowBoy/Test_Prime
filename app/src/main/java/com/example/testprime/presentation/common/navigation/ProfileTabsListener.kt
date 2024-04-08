package com.dot.prime.presentation.common.navigation

interface ProfileTabsListener {

    fun getCurrentProfileTab(): ProfileTab

    fun setProfileTab(tab: ProfileTab)

}