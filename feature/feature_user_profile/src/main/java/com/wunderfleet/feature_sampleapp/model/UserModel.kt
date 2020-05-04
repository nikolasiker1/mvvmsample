package com.wunderfleet.feature_sampleapp.model

import android.os.Parcelable
import com.wunderfleet.domain_sampleapp.model.GithubUserModel
import kotlinx.android.parcel.Parcelize

@Parcelize
data class UserModel(
    val login: String?,
    val id: Int?,
    val node_id: String?,
    val avatar_url: String?,
    val gravatar_id: String?,
    val url: String?,
    val html_url: String?,
    val followers_url: String?,
    val following_url: String?,
    val gists_url: String?,
    val starred_url: String?,
    val subscriptions_url: String?,
    val organizations_url: String?,
    val repos_url: String?,
    val events_url: String?,
    val received_events_url: String?,
    val type: String?,
    val site_admin: Boolean?,
    val name: String?,
    val company: String?,
    val blog: String?,
    val location: String?,
    val email: String?,
    val hireable: Boolean?,
    val bio: String?,
    val public_repos: Int?,
    val public_gists: Int?,
    val followers: Int?,
    val following: Int?,
    val created_at: String?,
    val updated_at: String?
) : Parcelable

fun GithubUserModel.mapToView(): UserModel = UserModel(
    this.login,
    this.id,
    this.node_id,
    this.avatar_url,
    this.gravatar_id,
    this.url,
    this.html_url,
    this.followers_url,
    this.following_url,
    this.gists_url,
    this.starred_url,
    this.subscriptions_url,
    this.organizations_url,
    this.repos_url,
    this.events_url,
    this.received_events_url,
    this.type,
    this.site_admin,
    this.name,
    this.company,
    this.blog,
    this.location,
    this.email,
    this.hireable,
    this.bio,
    this.public_repos,
    this.public_gists,
    this.followers,
    this.following,
    this.created_at,
    this.updated_at
)