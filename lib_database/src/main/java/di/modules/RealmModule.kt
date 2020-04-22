package di.modules

import com.wunderfleet.core.di.scopes.DataScope
import dagger.Module
import dagger.Provides
import io.realm.Realm
import io.realm.RealmConfiguration
import io.realm.annotations.RealmModule

@Module
class RealmModule {

    @DataScope
    @Provides
    fun provideRealm(): Realm = Realm.getDefaultInstance()

}