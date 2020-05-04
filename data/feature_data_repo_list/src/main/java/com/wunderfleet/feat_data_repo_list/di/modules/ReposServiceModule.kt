package com.wunderfleet.feat_data_repo_list.di.modules

import com.wunderfleet.feat_data_repo_list.di.ReposDataScope
import com.wunderfleet.feat_data_repo_list.remote.RepoService
import com.wunderfleet.network.di.modules.NetworkModule
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

@Module(includes = [NetworkModule::class])
class ReposServiceModule {
    @ReposDataScope
    @Provides
    fun provideRetrofitService(retrofit: Retrofit): RepoService {
        return retrofit.create(RepoService::class.java)
    }
}