package com.wunderfleet.network.di.components

import com.wunderfleet.core.di.components.CoreComponent
import com.wunderfleet.network.di.NetworkScope
import com.wunderfleet.network.di.modules.NetworkModule
import dagger.Component
import retrofit2.Retrofit

@NetworkScope
@Component(modules = [NetworkModule::class], dependencies = [CoreComponent::class])
interface NetworkComponent : CoreComponent {
    fun getRetrofit(): Retrofit
}