package com.wunderfleet.core.domain.usecase

import io.reactivex.disposables.CompositeDisposable

abstract class UseCase<T> {
    protected val compositeDisposable = CompositeDisposable()

    abstract fun executeUseCase(onStatus: (status: T) -> Unit)

    open fun clear() {
        compositeDisposable.clear()
    }
}