package com.wunderfleet.domain_sampleapp.model

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey
import io.realm.annotations.RealmClass

@RealmClass
open class GithubUserModel(
    var id: Int? = null,
    @PrimaryKey
    var login: String? = null,
    var node_id: String? = null,
    var avatar_url: String? = null,
    var gravatar_id: String? = null,
    var url: String? = null,
    var html_url: String? = null,
    var followers_url: String? = null,
    var following_url: String? = null,
    var gists_url: String? = null,
    var starred_url: String? = null,
    var subscriptions_url: String? = null,
    var organizations_url: String? = null,
    var repos_url: String? = null,
    var events_url: String? = null,
    var received_events_url: String? = null,
    var type: String? = null,
    var site_admin: Boolean? = null,
    var name: String? = null,
    var company: String? = null,
    var blog: String? = null,
    var location: String? = null,
    var email: String? = null,
    var hireable: Boolean? = null,
    var bio: String? = null,
    var public_repos: Int? = null,
    var public_gists: Int? = null,
    var followers: Int? = null,
    var following: Int? = null,
    var created_at: String? = null,
    var updated_at: String? = null
) : RealmObject()