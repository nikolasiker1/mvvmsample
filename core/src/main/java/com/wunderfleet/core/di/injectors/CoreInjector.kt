package com.wunderfleet.core.di.injectors

import android.app.Application
import com.wunderfleet.core.di.components.CoreComponent
import com.wunderfleet.core.di.components.DaggerCoreComponent
import com.wunderfleet.core.di.modules.CoreModule

object CoreInjector {
    lateinit var coreComponent: CoreComponent

    fun initialize(application: Application) {
        coreComponent = DaggerCoreComponent.builder()
            .coreModule(CoreModule(application))
            .build()
    }
}