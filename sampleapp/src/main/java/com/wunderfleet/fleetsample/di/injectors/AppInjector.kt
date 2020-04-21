package com.wunderfleet.fleetsample.di.injectors

import android.app.Application
import com.wunderfleet.core.di.injectors.CoreInjector
import com.wunderfleet.data_sampleapp.di.injectors.UserDataInjector
import com.wunderfleet.feature_sampleapp.di.injectors.UserInjector
import com.wunderfleet.network.di.injectors.NetworkInjector
import di.injectors.RealmInjector

object AppInjector {
    fun initialize(application: Application) {
        initializeCore(application)
        initNetwork()
        initDatabase()
        initializeUserData()
        initUser()
    }

    private fun initializeCore(application: Application) {
        CoreInjector.initialize(application)
    }

    private fun initializeUserData() {
        UserDataInjector.initialize()
    }

    private fun initUser() {
        UserInjector.initialize()
    }

    private fun initNetwork() {
        NetworkInjector.initialize()
    }

    private fun initDatabase() {
        RealmInjector.initialize()
    }
}