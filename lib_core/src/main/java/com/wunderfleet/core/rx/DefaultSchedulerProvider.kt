package com.wunderfleet.core.rx

import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class DefaultSchedulerProvider : SchedulerProvider {
    override fun getMainThread(): Scheduler {
        return AndroidSchedulers.mainThread()
    }

    override fun getIoThread(): Scheduler {
        return Schedulers.io()
    }

    override fun getNewThread(): Scheduler {
        return Schedulers.newThread()
    }

}