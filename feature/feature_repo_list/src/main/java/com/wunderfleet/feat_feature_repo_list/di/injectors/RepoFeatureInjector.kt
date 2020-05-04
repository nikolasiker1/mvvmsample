package com.wunderfleet.feat_feature_repo_list.di.injectors

import com.wunderfleet.feat_domain_repo_list.di.injectors.RepoListDomainInjector
import com.wunderfleet.feat_feature_repo_list.di.components.DaggerRepoFeatureComponent
import com.wunderfleet.feat_feature_repo_list.di.components.RepoFeatureComponent

object RepoFeatureInjector {
    lateinit var component: RepoFeatureComponent

    fun initialize() {
        component = DaggerRepoFeatureComponent.builder()
            .repoListDomainComponent(RepoListDomainInjector.component)
            .build()
    }
}