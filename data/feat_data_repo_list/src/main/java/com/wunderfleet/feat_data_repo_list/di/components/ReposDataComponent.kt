package com.wunderfleet.feat_data_repo_list.di.components

import com.wunderfleet.core.di.components.CoreComponent
import com.wunderfleet.core.di.scopes.DataScope
import com.wunderfleet.feat_data_repo_list.di.ReposDataScope
import com.wunderfleet.feat_data_repo_list.di.modules.ReposDataModule
import com.wunderfleet.feat_domain_repo_list.repository.GithubRepoRepository
import dagger.Component
import javax.inject.Provider

@ReposDataScope
@DataScope
@Component(
    modules = [ReposDataModule::class],
    dependencies = [CoreComponent::class]
)
interface ReposDataComponent : CoreComponent {
    fun getReposRepository(): Provider<GithubRepoRepository>
}