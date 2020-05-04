package com.wunderfleet.domain_sampleapp.repository

import com.wunderfleet.domain_sampleapp.model.GithubUserModel
import io.reactivex.Single

interface UserRepository {
    fun getUser(username: String): Single<GithubUserModel>
}