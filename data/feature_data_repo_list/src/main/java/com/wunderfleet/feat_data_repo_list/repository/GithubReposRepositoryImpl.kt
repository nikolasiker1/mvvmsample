package com.wunderfleet.feat_data_repo_list.repository

import com.wunderfleet.core.rx.SchedulerProvider
import com.wunderfleet.feat_domain_repo_list.model.GithubRepoModel
import com.wunderfleet.feat_domain_repo_list.repository.GithubRepoRepository
import io.reactivex.Single
import javax.inject.Inject

open class GithubReposRepositoryImpl @Inject constructor(
    private val remoteDataSource: ReposRemoteDataSource,
    private val localDataSource: ReposLocalDataSource,
    private val schedulerProvider: SchedulerProvider
) : GithubRepoRepository {
    override fun getAllRepos(username: String): Single<List<GithubRepoModel>> {
        return remoteDataSource
            .getRepos(username)
            .subscribeOn(schedulerProvider.getIoThread())
            .observeOn(schedulerProvider.getMainThread())
            .flatMap { items -> localDataSource.insertRepo(items) }
            .flatMap {
                localDataSource.getAllRepos(username)
            }
    }
}