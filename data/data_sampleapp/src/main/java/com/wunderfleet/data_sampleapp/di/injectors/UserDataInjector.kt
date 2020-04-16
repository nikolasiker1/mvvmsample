package com.wunderfleet.data_sampleapp.di.injectors

import com.wunderfleet.core.di.injectors.CoreInjector
import com.wunderfleet.data_sampleapp.di.components.DaggerUserDataComponent
import com.wunderfleet.data_sampleapp.di.components.UserDataComponent
import com.wunderfleet.fleetsample.di.injectors.SampleappDomainInjector


object UserDataInjector {
    lateinit var component: UserDataComponent

    fun initialize() {
        component = DaggerUserDataComponent.builder()
            .coreComponent(CoreInjector.coreComponent).build()

        SampleappDomainInjector.initialize(
            component.getUserRepository(),
            component.getSchedulerProvider()
        )
    }
}