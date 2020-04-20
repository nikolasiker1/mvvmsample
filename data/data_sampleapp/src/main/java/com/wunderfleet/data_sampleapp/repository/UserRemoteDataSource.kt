package com.wunderfleet.data_sampleapp.repository

import com.wunderfleet.domain_sampleapp.model.GithubUserModel
import io.reactivex.Single

interface UserRemoteDataSource {
    fun getUser(username: String): Single<GithubUserModel>
}