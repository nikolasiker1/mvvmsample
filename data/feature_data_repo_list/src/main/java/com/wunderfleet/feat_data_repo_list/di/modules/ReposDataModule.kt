package com.wunderfleet.feat_data_repo_list.di.modules

import com.wunderfleet.feat_data_repo_list.db.ReposLocalDataSourceImpl
import com.wunderfleet.feat_data_repo_list.di.ReposDataScope
import com.wunderfleet.feat_data_repo_list.remote.RepoService
import com.wunderfleet.feat_data_repo_list.remote.ReposRemoteDataSourceImpl
import com.wunderfleet.feat_data_repo_list.repository.GithubReposRepositoryImpl
import com.wunderfleet.feat_data_repo_list.repository.ReposLocalDataSource
import com.wunderfleet.feat_data_repo_list.repository.ReposRemoteDataSource
import com.wunderfleet.feat_domain_repo_list.repository.GithubRepoRepository
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

@Module(includes = [ReposDataModule.BindModule::class, ReposServiceModule::class, RealmModule::class, NetworkModule::class])
class ReposDataModule {

    @ReposDataScope
    @Provides
    fun provideNetworkComponent(): NetworkComponent = NetworkInjector.networkComponent

    @ReposDataScope
    @Provides
    fun provideRealmComponent(): RealmComponent = RealmInjector.component

    @ReposDataScope
    @Provides
    fun provideUserRemoteDataSource(userService: RepoService): ReposRemoteDataSource {
        return ReposRemoteDataSourceImpl(userService)
    }

    @ReposDataScope
    @Provides
    fun provideUserLocalDataSource(realm: Realm): ReposLocalDataSource {
        return ReposLocalDataSourceImpl(realm)
    }

    @Module
    interface BindModule {
        @Binds
        fun bindRepository(repository: GithubReposRepositoryImpl): GithubRepoRepository
    }
}