package com.wunderfleet.feature_sampleapp.di.injectors

import com.wunderfleet.domain_sampleapp.di.injectors.SampleappDomainInjector
import com.wunderfleet.feature_sampleapp.di.components.DaggerUserComponent
import com.wunderfleet.feature_sampleapp.di.components.UserComponent

object UserInjector {

    lateinit var component: UserComponent

    fun initialize() {
        component = DaggerUserComponent.builder()
            .sampleappDomainComponent(SampleappDomainInjector.component)
            .build()
    }
}