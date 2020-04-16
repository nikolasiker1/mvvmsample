package com.wunderfleet.data_sampleapp.remote

import android.content.Context
import com.google.gson.Gson
import com.wunderfleet.data_sampleapp.repository.UserRemoteDataSource
import com.wunderfleet.fleetsample.model.GithubUserModel
import io.reactivex.Single
import java.io.InputStream
import java.lang.RuntimeException
import javax.inject.Inject
import javax.inject.Named

class FakeRemoteDataSource @Inject constructor(
    private val context: Context,
    private val gson: Gson,
    @Named("user_json_path") private val userJsonPath: String
) : UserRemoteDataSource {
    override fun getUser(): Single<GithubUserModel> {
        return Single.create<GithubUserModel> {
            val inputStream: InputStream = context.assets.open(userJsonPath)
            val inputString = inputStream.bufferedReader().use { reader -> reader.readText() }

            val user = gson.fromJson<GithubUserModel>(inputString, GithubUserModel::class.java)

            if (user != null) {
                it.onSuccess(user)
            } else {
                it.onError(RuntimeException("Couldn't load user!"))
            }
        }
    }
}