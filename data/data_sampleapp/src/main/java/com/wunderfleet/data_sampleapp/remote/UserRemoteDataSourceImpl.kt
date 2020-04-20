package com.wunderfleet.data_sampleapp.remote

import com.wunderfleet.data_sampleapp.repository.UserRemoteDataSource
import com.wunderfleet.domain_sampleapp.model.GithubUserModel
import io.reactivex.Single
import javax.inject.Inject

class UserRemoteDataSourceImpl @Inject constructor(
    private val userService: UserService
) : UserRemoteDataSource {
    override fun getUser(username: String): Single<GithubUserModel> {
        return userService.getUser(username)
    }
}