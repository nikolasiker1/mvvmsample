package com.wunderfleet.feat_data_repo_list.di.injectors

import com.wunderfleet.core.di.injectors.CoreInjector
import com.wunderfleet.feat_data_repo_list.di.components.DaggerReposDataComponent
import com.wunderfleet.feat_data_repo_list.di.components.ReposDataComponent
import com.wunderfleet.feat_domain_repo_list.di.injectors.RepoListDomainInjector

object ReposDataInjector {
    lateinit var component: ReposDataComponent

    fun initialize() {
        component =
            DaggerReposDataComponent.builder()
                .coreComponent(CoreInjector.coreComponent)
                .build()

        RepoListDomainInjector.initialize(
            component.getReposRepository(),
            component.getSchedulerProvider()
        )
    }
}