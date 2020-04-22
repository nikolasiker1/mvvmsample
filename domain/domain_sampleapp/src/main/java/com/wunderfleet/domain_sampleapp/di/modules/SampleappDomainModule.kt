package com.wunderfleet.domain_sampleapp.di.modules

import com.wunderfleet.core.rx.SchedulerProvider
import com.wunderfleet.domain_sampleapp.repository.UserRepository
import dagger.Module
import dagger.Provides
import javax.inject.Provider

@Module
class SampleappDomainModule(
    private val repository: Provider<UserRepository>,
    private val schedulerProvider: SchedulerProvider
) {
    @Provides
    fun provideUserRepository(): UserRepository = repository.get()

    @Provides
    fun provideSchedulerProvider(): SchedulerProvider = schedulerProvider
}