package com.example.testprime.presentation.app

import android.app.Application
import android.util.Log
import androidx.appcompat.app.AppCompatDelegate
import com.example.testprime.common.DevProd.Companion.YANDEX_MAP_API_KEY
import com.yandex.mapkit.MapKitFactory
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class MyApplication: Application() {

    override fun onCreate() {
        super.onCreate()

        // Off Dark mode
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)

        // Yandex mapkit
        MapKitFactory.setApiKey(YANDEX_MAP_API_KEY)

    }

}