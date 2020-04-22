package com.wunderfleet.fleetsample

import androidx.multidex.MultiDexApplication
import com.wunderfleet.domain_sampleapp.db.RealmUserModule
import com.wunderfleet.feat_domain_repo_list.db.RealmRepoListModule
import com.wunderfleet.feature_sampleapp.di.injectors.UserInjector
import com.wunderfleet.fleetsample.di.injectors.AppInjector
import io.realm.Realm
import io.realm.RealmConfiguration

class SampleApplication : MultiDexApplication() {
    override fun onCreate() {
        super.onCreate()

        Realm.init(this)

        val realmConfiguration = RealmConfiguration.Builder()
            .name(packageName)
            .modules(RealmUserModule(), RealmRepoListModule(), Realm.getDefaultModule())
            .deleteRealmIfMigrationNeeded()
            .build()

        Realm.setDefaultConfiguration(realmConfiguration)


        initializeDagger()
    }

    private fun initializeDagger() {
        AppInjector.initialize(this)
    }
}