package di.modules

import dagger.Module
import dagger.Provides
import di.RealmScope
import io.realm.Realm

@Module
class RealmModule {

    @RealmScope
    @Provides
    fun provideRealm(): Realm = Realm.getDefaultInstance()

}