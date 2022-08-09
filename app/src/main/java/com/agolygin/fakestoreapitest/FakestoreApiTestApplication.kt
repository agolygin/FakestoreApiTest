package com.agolygin.fakestoreapitest

import android.app.Application
import com.agolygin.fakestoreapitest.di.AppComponent
import com.agolygin.fakestoreapitest.di.DaggerAppComponent

class FakestoreApiTestApplication : Application() {

    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()

        appComponent = DaggerAppComponent.builder().build()
    }
}