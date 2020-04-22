package com.wunderfleet.feat_data_repo_list.db

import com.google.gson.Gson
import com.wunderfleet.core.extensions.executeAsync
import com.wunderfleet.feat_data_repo_list.repository.ReposLocalDataSource
import com.wunderfleet.feat_domain_repo_list.model.GithubRepoModel
import io.reactivex.Single
import io.realm.Realm
import javax.inject.Inject

class ReposLocalDataSourceImpl @Inject constructor(private val realm: Realm): ReposLocalDataSource {
    override fun insertRepo(repoModels: List<GithubRepoModel>): Single<Boolean> {

        return realm.executeAsync { r ->

            val repoList = mutableListOf<GithubRepoModel>()
            repoList.addAll(repoModels)
            r.insertOrUpdate(repoList)
        }
    }

    override fun getAllRepos(username: String): Single<List<GithubRepoModel>> {
        return Single.just(
            realm.copyFromRealm(
                realm.where(GithubRepoModel::class.java).findAll()
            )
        )
    }

}