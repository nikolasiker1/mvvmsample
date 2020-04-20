package com.wunderfleet.data_sampleapp.remote

import com.wunderfleet.domain_sampleapp.model.GithubUserModel
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path

interface UserService {
    @GET("/users/{username}")
    fun getUser(@Path("username") username: String): Single<GithubUserModel>
}