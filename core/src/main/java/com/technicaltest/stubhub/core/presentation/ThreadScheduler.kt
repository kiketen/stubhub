package com.technicaltest.stubhub.core.presentation

import io.reactivex.Scheduler

interface ThreadScheduler {
    fun getMainThread(): Scheduler
    fun getIoThread(): Scheduler
}