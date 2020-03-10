package com.oreakintobi.oreakintobi

import android.app.Application
import timber.log.Timber


class AppApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        Timber.plant(Timber.DebugTree())
    }
}