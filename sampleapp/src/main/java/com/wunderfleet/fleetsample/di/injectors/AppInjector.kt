package com.wunderfleet.fleetsample.di.injectors

import android.app.Application
import com.wunderfleet.core.di.injectors.CoreInjector

object AppInjector {
    fun initialize(application: Application) {
        initializeCore(application)
    }

    private fun initializeCore(application: Application) {
        CoreInjector.initialize(application)
    }
}