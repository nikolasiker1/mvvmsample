package com.wunderfleet.feat_domain_repo_list.db

import com.wunderfleet.feat_domain_repo_list.model.GithubRepoModel
import com.wunderfleet.feat_domain_repo_list.model.OwnerModel
import io.realm.annotations.RealmModule

@RealmModule(library = true, classes = [GithubRepoModel::class, OwnerModel::class])
class RealmRepoListModule {}