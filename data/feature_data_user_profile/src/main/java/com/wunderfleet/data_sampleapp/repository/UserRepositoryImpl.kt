package com.wunderfleet.data_sampleapp.repository

import com.wunderfleet.core.rx.SchedulerProvider
import com.wunderfleet.domain_sampleapp.model.GithubUserModel
import com.wunderfleet.domain_sampleapp.repository.UserRepository
import io.reactivex.Single
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val remoteDataSource: UserRemoteDataSource,
    private val localDataSource: UserLocalDataSource,
    private val schedulerProvider: SchedulerProvider
) : UserRepository {
    override fun getUser(username: String): Single<GithubUserModel> {
        return remoteDataSource
            .getUser(username)
            .subscribeOn(schedulerProvider.getIoThread())
            .observeOn(schedulerProvider.getMainThread())
            .flatMap {
                localDataSource.insertUser(it)
            }
            .flatMap {
                localDataSource.getUser(username)
            }
    }
}