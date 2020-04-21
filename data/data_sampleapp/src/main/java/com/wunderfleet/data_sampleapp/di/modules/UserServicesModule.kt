package com.wunderfleet.data_sampleapp.di.modules

import com.wunderfleet.data_sampleapp.di.UserDataScope
import com.wunderfleet.data_sampleapp.remote.UserService
import com.wunderfleet.network.di.modules.NetworkModule
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit


@Module(includes = [NetworkModule::class])
class UserServicesModule {
    @UserDataScope
    @Provides
    fun provideRetrofitService(retrofit: Retrofit): UserService {
        return retrofit.create(UserService::class.java)
    }
}