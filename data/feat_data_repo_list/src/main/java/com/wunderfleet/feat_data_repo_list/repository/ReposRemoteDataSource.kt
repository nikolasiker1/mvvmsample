package com.wunderfleet.feat_data_repo_list.repository

import com.wunderfleet.feat_domain_repo_list.model.GithubRepoModel
import io.reactivex.Single

interface ReposRemoteDataSource {
    fun getRepos(username: String): Single<List<GithubRepoModel>>
}