package com.wunderfleet.feature_sampleapp.di.components

import com.wunderfleet.core.presentation.ViewModelFactory
import com.wunderfleet.domain_sampleapp.di.components.SampleappDomainComponent
import com.wunderfleet.feature_sampleapp.di.UserScope
import com.wunderfleet.feature_sampleapp.di.modules.UserModule
import com.wunderfleet.feature_sampleapp.viewmodel.LoadUserViewModel
import dagger.Component

@UserScope
@Component(dependencies = [SampleappDomainComponent::class], modules = [UserModule::class])
interface UserComponent {
    fun getUserViewModelFactory(): ViewModelFactory<LoadUserViewModel>
}