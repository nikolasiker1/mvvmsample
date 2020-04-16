package com.wunderfleet.core.rx

import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

fun Disposable.disposeWith(disposables: CompositeDisposable) {
    disposables.add(this)
}