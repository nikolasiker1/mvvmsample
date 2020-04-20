package com.wunderfleet.data_sampleapp.di.components

import com.wunderfleet.data_sampleapp.di.UserDataScope
import com.wunderfleet.data_sampleapp.di.modules.UserDataModule
import com.wunderfleet.domain_sampleapp.repository.UserRepository
import com.wunderfleet.network.di.components.NetworkComponent
import dagger.Component
import javax.inject.Provider

@UserDataScope
@Component(
    modules = [UserDataModule::class],
    dependencies = [NetworkComponent::class]
)
interface UserDataComponent : NetworkComponent {
    fun getUserRepository(): Provider<UserRepository>
}