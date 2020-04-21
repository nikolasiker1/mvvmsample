package com.wunderfleet.fleetsample

import android.app.Application
import com.wunderfleet.feature_sampleapp.di.injectors.UserInjector
import com.wunderfleet.fleetsample.di.injectors.AppInjector
import io.realm.Realm

class SampleApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        initializeDagger()
        initUser()
        Realm.init(this)
    }

    private fun initializeDagger() {
        AppInjector.initialize(this)
    }

    private fun initUser() {
        UserInjector.initialize()
    }
}