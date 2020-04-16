package com.wunderfleet.fleetsample.repository

import com.wunderfleet.fleetsample.model.GithubUserModel
import io.reactivex.Single

interface UserRepository {
    fun getUser(username: String): Single<GithubUserModel>
}