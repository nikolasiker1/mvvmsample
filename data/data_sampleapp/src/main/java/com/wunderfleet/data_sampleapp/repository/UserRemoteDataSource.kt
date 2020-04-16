package com.wunderfleet.data_sampleapp.repository

import com.wunderfleet.fleetsample.model.GithubUserModel
import io.reactivex.Single

interface UserRemoteDataSource {
    fun getUser(): Single<GithubUserModel>
}