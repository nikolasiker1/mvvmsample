package com.wunderfleet.feat_feature_repo_list.di.modules

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.wunderfleet.core.presentation.ViewModelFactory
import com.wunderfleet.feat_feature_repo_list.di.RepoScope
import com.wunderfleet.feat_feature_repo_list.viewmodel.LoadReposViewModel
import dagger.Binds
import dagger.MapKey
import dagger.Module
import dagger.multibindings.IntoMap
import kotlin.reflect.KClass

@Module
abstract class RepoFeatureModule {
    @Target(
        AnnotationTarget.FUNCTION,
        AnnotationTarget.PROPERTY_GETTER,
        AnnotationTarget.PROPERTY_SETTER
    )

    @RepoScope
    @kotlin.annotation.Retention(AnnotationRetention.RUNTIME)
    @MapKey
    internal annotation class ViewModelKey(val value: KClass<out ViewModel>)


    @RepoScope
    @Binds
    internal abstract fun bindViewModelFactory(factory: ViewModelFactory<LoadReposViewModel>): ViewModelProvider.Factory

    @RepoScope
    @Binds
    @IntoMap
    @ViewModelKey(LoadReposViewModel::class)
    internal abstract fun loadReposViewModel(viewModel: LoadReposViewModel): ViewModel
}