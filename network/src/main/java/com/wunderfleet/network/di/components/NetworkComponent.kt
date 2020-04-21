package com.wunderfleet.network.di.components

import com.wunderfleet.core.di.scopes.DataScope
import com.wunderfleet.network.di.NetworkScope
import com.wunderfleet.network.di.modules.NetworkModule
import dagger.Component
import retrofit2.Retrofit

@NetworkScope
@DataScope
@Component(modules = [NetworkModule::class])
interface NetworkComponent {
    fun getRetrofit(): Retrofit
}