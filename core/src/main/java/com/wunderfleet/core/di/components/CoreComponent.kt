package com.wunderfleet.core.di.components

import android.app.Application
import android.content.Context
import com.wunderfleet.core.di.modules.CoreModule
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [CoreModule::class])
interface CoreComponent {
    fun getContext(): Context
    fun getApplication(): Application
}