package com.wunderfleet.data_sampleapp.di.modules

import com.wunderfleet.data_sampleapp.db.UserLocalDataSourceImpl
import com.wunderfleet.data_sampleapp.di.UserDataScope
import com.wunderfleet.data_sampleapp.remote.UserRemoteDataSourceImpl
import com.wunderfleet.data_sampleapp.remote.UserService
import com.wunderfleet.data_sampleapp.repository.UserLocalDataSource
import com.wunderfleet.data_sampleapp.repository.UserRemoteDataSource
import com.wunderfleet.data_sampleapp.repository.UserRepositoryImpl
import com.wunderfleet.domain_sampleapp.repository.UserRepository
import com.wunderfleet.network.di.components.NetworkComponent
import com.wunderfleet.network.di.injectors.NetworkInjector
import com.wunderfleet.network.di.modules.NetworkModule
import dagger.Binds
import dagger.Module
import dagger.Provides
import di.components.RealmComponent
import di.injectors.RealmInjector
import di.modules.RealmModule
import io.realm.Realm

@Module(includes = [UserDataModule.BindModule::class, UserServicesModule::class, RealmModule::class, NetworkModule::class])
class UserDataModule {

    @UserDataScope
    @Provides
    fun provideNetworkComponent(): NetworkComponent = NetworkInjector.networkComponent

    @UserDataScope
    @Provides
    fun provideRealmComponent(): RealmComponent = RealmInjector.component

    @UserDataScope
    @Provides
    fun provideUserRemoteDataSource(userService: UserService): UserRemoteDataSource {
        return UserRemoteDataSourceImpl(userService)
    }

    @UserDataScope
    @Provides
    fun provideUserLocalDataSource(realm: Realm): UserLocalDataSource {
        return UserLocalDataSourceImpl(realm)
    }

    @Module
    interface BindModule {
        @Binds
        fun bindRepository(repository: UserRepositoryImpl): UserRepository
    }
}