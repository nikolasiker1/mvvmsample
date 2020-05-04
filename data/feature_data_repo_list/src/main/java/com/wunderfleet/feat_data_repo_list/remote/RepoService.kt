package com.wunderfleet.feat_data_repo_list.remote

import com.wunderfleet.feat_domain_repo_list.model.GithubRepoModel
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path

interface RepoService {
    @GET("/users/{username}/repos")
    fun getRepos(@Path("username") username: String): Single<List<GithubRepoModel>>

}