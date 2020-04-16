package com.wunderfleet.fleetsample.di.injectors

import com.wunderfleet.core.rx.SchedulerProvider
import com.wunderfleet.fleetsample.di.components.DaggerSampleappDomainComponent
import com.wunderfleet.fleetsample.di.components.SampleappDomainComponent
import com.wunderfleet.fleetsample.di.modules.SampleappDomainModule
import com.wunderfleet.fleetsample.repository.UserRepository
import javax.inject.Provider

object SampleappDomainInjector {
    lateinit var component: SampleappDomainComponent

    fun initialize(repository: Provider<UserRepository>, schedulerProvider: SchedulerProvider) {
        component = DaggerSampleappDomainComponent.builder()
            .sampleappDomainModule(
                SampleappDomainModule(
                    repository = repository,
                    schedulerProvider = schedulerProvider
                )
            ).build()
    }
}