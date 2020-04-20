package com.wunderfleet.network.di.injectors

import com.wunderfleet.core.di.injectors.CoreInjector
import com.wunderfleet.network.di.components.DaggerNetworkComponent
import com.wunderfleet.network.di.components.NetworkComponent

object NetworkInjector {
    lateinit var networkComponent: NetworkComponent

    fun initialize() {
        networkComponent = DaggerNetworkComponent
            .builder()
            .coreComponent(CoreInjector.coreComponent)
            .build()
    }
}