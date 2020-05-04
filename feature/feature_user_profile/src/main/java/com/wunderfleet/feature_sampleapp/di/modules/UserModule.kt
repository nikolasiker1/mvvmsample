package com.wunderfleet.feature_sampleapp.di.modules

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.wunderfleet.core.presentation.ViewModelFactory
import com.wunderfleet.feature_sampleapp.di.UserScope
import com.wunderfleet.feature_sampleapp.viewmodel.LoadUserViewModel
import dagger.Binds
import dagger.MapKey
import dagger.Module
import dagger.multibindings.IntoMap
import kotlin.reflect.KClass


@Module
abstract class UserModule {
    @Target(
        AnnotationTarget.FUNCTION,
        AnnotationTarget.PROPERTY_GETTER,
        AnnotationTarget.PROPERTY_SETTER
    )

    @UserScope
    @kotlin.annotation.Retention(AnnotationRetention.RUNTIME)
    @MapKey
    internal annotation class ViewModelKey(val value: KClass<out ViewModel>)


    @UserScope
    @Binds
    internal abstract fun bindViewModelFactory(factory: ViewModelFactory<LoadUserViewModel>): ViewModelProvider.Factory

    @UserScope
    @Binds
    @IntoMap
    @ViewModelKey(LoadUserViewModel::class)
    internal abstract fun loadUserViewModel(viewModel: LoadUserViewModel): ViewModel
}