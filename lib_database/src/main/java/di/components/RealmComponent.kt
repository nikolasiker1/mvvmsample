package di.components

import dagger.Component
import di.RealmScope
import di.modules.RealmModule
import io.realm.Realm

@RealmScope
@Component(modules = [RealmModule::class])
interface RealmComponent {
    fun getRealm(): Realm
}