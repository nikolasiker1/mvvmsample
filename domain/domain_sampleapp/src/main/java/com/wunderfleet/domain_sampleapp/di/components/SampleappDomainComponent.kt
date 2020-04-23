package com.wunderfleet.domain_sampleapp.di.components

import com.wunderfleet.domain_sampleapp.di.modules.SampleappDomainModule
import com.wunderfleet.domain_sampleapp.usecase.GetUserUsecase
import dagger.Component


@Component(modules = [SampleappDomainModule::class])
interface SampleappDomainComponent {
    fun getUserUsecase(): GetUserUsecase
}