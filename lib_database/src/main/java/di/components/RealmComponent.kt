package di.components

import com.wunderfleet.core.di.scopes.DataScope
import dagger.Component
import di.DbScope
import di.modules.RealmModule
import io.realm.Realm

@DbScope
@DataScope
@Component(modules = [RealmModule::class])
interface RealmComponent {
    fun getRealm(): Realm
}