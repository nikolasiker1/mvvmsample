package com.wunderfleet.feat_feature_repo_list.model

import android.os.Parcelable
import com.wunderfleet.feat_domain_repo_list.model.OwnerModel
import kotlinx.android.parcel.Parcelize

@Parcelize
class OwnerModelView(
    var login: String? = null,
    var id: Int? = null,
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
    var site_admin: Boolean? = null
) : Parcelable

fun OwnerModel.mapToView(): OwnerModelView = OwnerModelView(
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
    this.site_admin
)