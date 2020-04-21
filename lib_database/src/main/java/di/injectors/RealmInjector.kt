package di.injectors

import di.components.DaggerRealmComponent
import di.components.RealmComponent

object RealmInjector {
    lateinit var component: RealmComponent

    fun initialize() {
        component = DaggerRealmComponent.builder()
            .build()
    }
}