package com.wunderfleet.data_sampleapp.di.modules

import android.content.Context
import com.google.gson.Gson
import com.wunderfleet.data_sampleapp.di.UserDataScope
import com.wunderfleet.data_sampleapp.remote.FakeRemoteDataSource
import com.wunderfleet.data_sampleapp.repository.UserRemoteDataSource
import com.wunderfleet.data_sampleapp.repository.UserRepositoryImpl
import com.wunderfleet.fleetsample.repository.UserRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import javax.inject.Named

@Module(includes = [UserDataModule.BindModule::class])
class UserDataModule {

    @Provides
    @UserDataScope
    @Named("user_json_path")
    fun provideJsonPath(): String = "user.json"

    @Provides
    fun provideFakeDataSource(
        context: Context,
        gson: Gson,
        @Named("user_json_path") userJsonPath: String
    ): UserRemoteDataSource {
        return FakeRemoteDataSource(context, gson, userJsonPath)
    }

    @Module
    interface BindModule {

        @Binds
        fun bindRepository(repository: UserRepositoryImpl): UserRepository


    }
}