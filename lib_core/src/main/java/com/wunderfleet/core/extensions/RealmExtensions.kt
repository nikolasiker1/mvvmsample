package com.wunderfleet.core.extensions

import android.os.Looper
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.realm.Realm

fun Realm.executeAsync(transaction: (Realm) -> Unit): Single<Boolean> {
    return Single.create<Boolean> { emitter ->
        executeTransactionAsync(transaction, {
            emitter.onSuccess(true)
        }, {
            it.printStackTrace()
            emitter.onError(it)
        })
    }
}

fun Realm.executeAsyncOnMainThread(transaction: (Realm) -> Unit): Single<Boolean> {
    return Single.just(true)
        .observeOn(AndroidSchedulers.from(Looper.getMainLooper()))
        .flatMap { _ ->
            Single.create<Boolean> { emitter ->
                executeTransactionAsync(transaction, {
                    emitter.onSuccess(true)
                }, {
                    emitter.onError(it)
                })
            }
        }
}