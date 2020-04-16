package com.wunderfleet.data_sampleapp.di.components

import com.wunderfleet.core.di.components.CoreComponent
import com.wunderfleet.data_sampleapp.di.UserDataScope
import com.wunderfleet.data_sampleapp.di.modules.UserDataModule
import com.wunderfleet.fleetsample.repository.UserRepository
import dagger.Component
import javax.inject.Provider

@UserDataScope
@Component(modules = [UserDataModule::class], dependencies = [CoreComponent::class])
interface UserDataComponent : CoreComponent {
    fun getUserRepository(): Provider<UserRepository>
}