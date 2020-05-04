package com.wunderfleet.feat_feature_repo_list.di.components

import com.wunderfleet.core.presentation.ViewModelFactory
import com.wunderfleet.feat_domain_repo_list.di.components.RepoListDomainComponent
import com.wunderfleet.feat_feature_repo_list.di.RepoScope
import com.wunderfleet.feat_feature_repo_list.di.modules.RepoFeatureModule
import com.wunderfleet.feat_feature_repo_list.viewmodel.LoadReposViewModel
import dagger.Component

@RepoScope
@Component(dependencies = [RepoListDomainComponent::class], modules = [RepoFeatureModule::class])
interface RepoFeatureComponent {
    fun getReposViewModelFactory(): ViewModelFactory<LoadReposViewModel>
}