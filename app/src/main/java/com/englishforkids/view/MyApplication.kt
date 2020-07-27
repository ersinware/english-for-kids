package com.englishforkids.view

import android.app.Application
import android.content.Context

lateinit var appContext: Context

class MyApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        appContext = applicationContext
    }
}