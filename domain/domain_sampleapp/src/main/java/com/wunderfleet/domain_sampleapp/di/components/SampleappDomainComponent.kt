package com.wunderfleet.fleetsample.di.components

import com.wunderfleet.fleetsample.di.modules.SampleappDomainModule
import com.wunderfleet.domain_sampleapp.usecase.GetUserUsecase
import dagger.Component


@Component(modules = [SampleappDomainModule::class])
interface SampleappDomainComponent {
    fun getUserUsecase(): GetUserUsecase
}