package com.wunderfleet.feat_data_repo_list.repository

import com.wunderfleet.feat_domain_repo_list.model.GithubRepoModel
import io.reactivex.Single

interface ReposLocalDataSource {
    fun insertRepo(repoModels: List<GithubRepoModel>): Single<Boolean>
    fun getAllRepos(username: String): Single<List<GithubRepoModel>>
}