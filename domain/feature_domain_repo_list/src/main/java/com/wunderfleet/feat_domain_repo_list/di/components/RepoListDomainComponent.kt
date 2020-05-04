package com.wunderfleet.feat_domain_repo_list.di.components

import com.wunderfleet.feat_domain_repo_list.di.modules.RepoListDomainModule
import com.wunderfleet.feat_domain_repo_list.usecase.GetAllReposUsecase
import dagger.Component

@Component(modules = [RepoListDomainModule::class])
interface RepoListDomainComponent {
    fun getReposUsecase(): GetAllReposUsecase
}