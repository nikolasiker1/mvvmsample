package com.wunderfleet.fleetsample

import android.app.Application
import com.wunderfleet.fleetsample.di.injectors.AppInjector

class SampleApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        initializeDagger()
    }

    private fun initializeDagger() {
        AppInjector.initialize(this)
    }
}