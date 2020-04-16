package com.wunderfleet.core.di.components

import android.app.Application
import android.content.Context
import com.google.gson.Gson
import com.wunderfleet.core.di.modules.CoreModule
import com.wunderfleet.core.rx.SchedulerProvider
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [CoreModule::class])
interface CoreComponent {
    fun getContext(): Context
    fun getApplication(): Application
    fun getGson(): Gson
    fun getSchedulerProvider(): SchedulerProvider
}