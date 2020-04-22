package com.wunderfleet.fleetsample.di.injectors

import android.app.Application
import com.wunderfleet.core.di.injectors.CoreInjector
import com.wunderfleet.data_sampleapp.di.injectors.UserDataInjector
import com.wunderfleet.feat_data_repo_list.di.injectors.ReposDataInjector
import com.wunderfleet.feat_feature_repo_list.di.injectors.RepoFeatureInjector
import com.wunderfleet.feature_sampleapp.di.injectors.UserInjector
import com.wunderfleet.network.di.injectors.NetworkInjector
import di.injectors.RealmInjector

object AppInjector {
    fun initialize(application: Application) {
        initializeCore(application)
        initNetwork()
        initDatabase()
        initializeUserData()
        initializeRepoListData()
        initUser()
        initRepos()
    }

    private fun initializeCore(application: Application) {
        CoreInjector.initialize(application)
    }

    private fun initializeUserData() {
        UserDataInjector.initialize()
    }

    private fun initUser() {
        UserInjector.initialize()
    }

    private fun initNetwork() {
        NetworkInjector.initialize()
    }

    private fun initDatabase() {
        RealmInjector.initialize()
    }

    private fun initializeRepoListData() {
        ReposDataInjector.initialize()
    }

    private fun initRepos() {
        RepoFeatureInjector.initialize()
    }
}