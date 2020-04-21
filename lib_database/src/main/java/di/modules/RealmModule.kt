package di.modules

import com.wunderfleet.core.di.scopes.DataScope
import dagger.Module
import dagger.Provides
import io.realm.Realm

@Module
class RealmModule {

    @DataScope
    @Provides
    fun provideRealm(): Realm = Realm.getDefaultInstance()

}