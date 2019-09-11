package com.github.titaniper.vehicles

import android.app.Application
import com.github.titaniper.vehicles.di.koinInjectModule
import org.koin.android.ext.android.startKoin
import timber.log.Timber

class BaseApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        Timber.plant(Timber.DebugTree())
        startKoin(applicationContext, koinInjectModule)
    }
}