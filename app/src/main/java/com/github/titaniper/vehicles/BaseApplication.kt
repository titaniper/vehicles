package com.github.titaniper.vehicles

import android.app.Application
import com.github.titaniper.vehicles.di.koinInjectModule
import org.koin.android.ext.android.startKoin

class BaseApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin(applicationContext, koinInjectModule)
    }
}