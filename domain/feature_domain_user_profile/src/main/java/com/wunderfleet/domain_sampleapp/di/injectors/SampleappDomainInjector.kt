package com.wunderfleet.domain_sampleapp.di.injectors

import com.wunderfleet.core.rx.SchedulerProvider
import com.wunderfleet.domain_sampleapp.di.components.DaggerSampleappDomainComponent
import com.wunderfleet.domain_sampleapp.di.components.SampleappDomainComponent
import com.wunderfleet.domain_sampleapp.di.modules.SampleappDomainModule
import com.wunderfleet.domain_sampleapp.repository.UserRepository
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