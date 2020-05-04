package com.wunderfleet.feat_domain_repo_list.di.injectors

import com.wunderfleet.core.rx.SchedulerProvider
import com.wunderfleet.feat_domain_repo_list.di.components.DaggerRepoListDomainComponent
import com.wunderfleet.feat_domain_repo_list.di.components.RepoListDomainComponent
import com.wunderfleet.feat_domain_repo_list.di.modules.RepoListDomainModule
import com.wunderfleet.feat_domain_repo_list.repository.GithubRepoRepository
import javax.inject.Provider

object RepoListDomainInjector {
    lateinit var component: RepoListDomainComponent

    fun initialize(
        repository: Provider<GithubRepoRepository>,
        schedulerProvider: SchedulerProvider
    ) {
        component = DaggerRepoListDomainComponent.builder()
            .repoListDomainModule(RepoListDomainModule(repository, schedulerProvider))
            .build()
    }
}