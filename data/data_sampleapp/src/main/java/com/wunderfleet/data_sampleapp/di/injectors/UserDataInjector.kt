package com.wunderfleet.data_sampleapp.di.injectors

import com.wunderfleet.data_sampleapp.di.components.DaggerUserDataComponent
import com.wunderfleet.data_sampleapp.di.components.UserDataComponent
import com.wunderfleet.domain_sampleapp.di.injectors.SampleappDomainInjector
import com.wunderfleet.network.di.injectors.NetworkInjector


object UserDataInjector {
    lateinit var component: UserDataComponent

    fun initialize() {
        component = DaggerUserDataComponent.builder()
            .networkComponent(NetworkInjector.networkComponent)
            .build()

        SampleappDomainInjector.initialize(
            component.getUserRepository(),
            component.getSchedulerProvider()
        )
    }
}