package com.wunderfleet.fleetsample

import android.app.Application
import com.wunderfleet.feature_sampleapp.di.injectors.UserInjector
import com.wunderfleet.fleetsample.di.injectors.AppInjector

class SampleApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        initializeDagger()
        initUser()
    }

    private fun initializeDagger() {
        AppInjector.initialize(this)
    }

    private fun initUser() {
        UserInjector.initialize()
    }
}