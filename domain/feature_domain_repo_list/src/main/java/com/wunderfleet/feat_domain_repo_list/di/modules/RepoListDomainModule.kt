package com.wunderfleet.feat_domain_repo_list.di.modules

import com.wunderfleet.core.rx.SchedulerProvider
import com.wunderfleet.feat_domain_repo_list.repository.GithubRepoRepository
import dagger.Module
import dagger.Provides
import javax.inject.Provider

@Module
class RepoListDomainModule(
    private val githubRepoRepository: Provider<GithubRepoRepository>,
    private val schedulerProvider: SchedulerProvider
) {
    @Provides
    fun provideUserRepository(): GithubRepoRepository = githubRepoRepository.get()

    @Provides
    fun provideSchedulerProvider(): SchedulerProvider = schedulerProvider
}