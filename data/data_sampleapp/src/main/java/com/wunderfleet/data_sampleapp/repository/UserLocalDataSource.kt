package com.wunderfleet.data_sampleapp.repository

import com.wunderfleet.domain_sampleapp.model.GithubUserModel
import io.reactivex.Single

interface UserLocalDataSource {
    fun insertUser(user: GithubUserModel): Single<Boolean>
    fun getUser(username: String): Single<GithubUserModel>
}