package com.example.burgerrestaurantapp

import android.app.Application
import com.example.burgerrestaurantapp.core.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class BurgersApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@BurgersApplication)
            modules(appModule)
        }
    }
}