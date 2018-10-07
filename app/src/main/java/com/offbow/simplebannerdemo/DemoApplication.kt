package com.offbow.simplebannerdemo

import android.app.Application
import com.offbow.simplebanner.SimpleBanner

open class DemoApplication:Application() {

    override fun onCreate() {
        super.onCreate()
        SimpleBanner.init(this)
    }
}