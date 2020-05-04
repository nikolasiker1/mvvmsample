package com.wunderfleet.core.di.modules

import android.app.Application
import android.content.Context
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.wunderfleet.core.rx.DefaultSchedulerProvider
import com.wunderfleet.core.rx.SchedulerProvider
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
open class CoreModule(private val application: Application) {

    @Provides
    @Singleton
    open fun provideContext(): Context = application

    @Provides
    @Singleton
    open fun provideApplication(): Application = application

    @Provides
    @Singleton
    open fun provideGson(): Gson {
        return GsonBuilder()
            .create()
    }

    @Provides
    @Singleton
    open fun provideSchedulerProvider(): SchedulerProvider = DefaultSchedulerProvider()
}