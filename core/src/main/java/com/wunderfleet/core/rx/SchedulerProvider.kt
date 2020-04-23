package com.wunderfleet.core.rx

import io.reactivex.Scheduler

interface SchedulerProvider {
    fun getMainThread(): Scheduler
    fun getIoThread(): Scheduler
    fun getNewThread(): Scheduler
}