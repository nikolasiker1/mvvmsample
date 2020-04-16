package com.wunderfleet.data_sampleapp.repository

import com.wunderfleet.core.rx.SchedulerProvider
import com.wunderfleet.fleetsample.model.GithubUserModel
import com.wunderfleet.fleetsample.repository.UserRepository
import io.reactivex.Single
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val remoteDataSource: UserRemoteDataSource,
    private val schedulerProvider: SchedulerProvider
) : UserRepository {
    override fun getUser(username: String): Single<GithubUserModel> {
        return remoteDataSource.getUser().subscribeOn(schedulerProvider.io)
    }
}