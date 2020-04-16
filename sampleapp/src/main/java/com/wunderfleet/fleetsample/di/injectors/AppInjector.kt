package com.wunderfleet.fleetsample.di.injectors

import android.app.Application
import com.wunderfleet.core.di.injectors.CoreInjector
import com.wunderfleet.data_sampleapp.di.injectors.UserDataInjector

object AppInjector {
    fun initialize(application: Application) {
        initializeCore(application)
        initializeUser()
    }

    private fun initializeCore(application: Application) {
        CoreInjector.initialize(application)
    }

    private fun initializeUser() {
        UserDataInjector.initialize()
    }
}