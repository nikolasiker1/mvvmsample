package com.wunderfleet.feat_data_repo_list.remote

import com.wunderfleet.feat_data_repo_list.repository.ReposRemoteDataSource
import com.wunderfleet.feat_domain_repo_list.model.GithubRepoModel
import io.reactivex.Single
import javax.inject.Inject

class ReposRemoteDataSourceImpl @Inject constructor(private val repoService: RepoService): ReposRemoteDataSource {
    override fun getRepos(username: String): Single<List<GithubRepoModel>> {
        return repoService.getRepos(username)
    }

}