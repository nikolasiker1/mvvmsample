package com.wunderfleet.domain_sampleapp.db

import com.wunderfleet.domain_sampleapp.model.GithubUserModel
import io.realm.annotations.RealmModule

@RealmModule(library = true, classes = [GithubUserModel::class])
class RealmUserModule {
}