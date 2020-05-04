package com.wunderfleet.data_sampleapp.db

import com.wunderfleet.core.extensions.executeAsync
import com.wunderfleet.data_sampleapp.repository.UserLocalDataSource
import com.wunderfleet.domain_sampleapp.model.GithubUserModel
import io.reactivex.Single
import io.realm.Realm
import javax.inject.Inject

class UserLocalDataSourceImpl @Inject constructor(private val realm: Realm) : UserLocalDataSource {

    override fun insertUser(user: GithubUserModel): Single<Boolean> {
        return realm.executeAsync { r ->
            r.insertOrUpdate(user)
        }
    }

    override fun getUser(username: String): Single<GithubUserModel> {
        return Single.just(
            realm.copyFromRealm(
                realm.where(GithubUserModel::class.java).equalTo("login", username).findFirst()
            )
        )
    }
}