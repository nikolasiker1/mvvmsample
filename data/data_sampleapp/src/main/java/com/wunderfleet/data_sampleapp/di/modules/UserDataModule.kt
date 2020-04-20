package com.wunderfleet.data_sampleapp.di.modules

import com.wunderfleet.data_sampleapp.di.UserDataScope
import com.wunderfleet.data_sampleapp.remote.UserRemoteDataSourceImpl
import com.wunderfleet.data_sampleapp.remote.UserService
import com.wunderfleet.data_sampleapp.repository.UserRemoteDataSource
import com.wunderfleet.data_sampleapp.repository.UserRepositoryImpl
import com.wunderfleet.domain_sampleapp.repository.UserRepository
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module(includes = [UserDataModule.BindModule::class, UserServicesModule::class])
class UserDataModule {

    @UserDataScope
    @Provides
    fun provideUserRemoteDataSource(userService: UserService): UserRemoteDataSource {
        return UserRemoteDataSourceImpl(userService)
    }

    @Module
    interface BindModule {

        @Binds
        fun bindRepository(repository: UserRepositoryImpl): UserRepository


    }
}