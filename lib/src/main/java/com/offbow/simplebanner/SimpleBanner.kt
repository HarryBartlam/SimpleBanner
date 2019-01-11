package com.offbow.simplebanner

import android.app.Activity
import android.app.Application
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.view.ViewGroup
import androidx.annotation.ColorInt

class SimpleBanner(application: Application,
                   @ColorInt
                   var color: Int,
                   var message: String) : Application.ActivityLifecycleCallbacks {

    init {
        application.registerActivityLifecycleCallbacks(this)
    }

    override fun onActivityCreated(activity: Activity, savedInstanceState: Bundle?) {
        val decorView = activity.window.decorView as ViewGroup

        val simpleBannerView = SimpleBannerView(activity, color, message)

        val layoutParams = ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT)
        decorView.addView(simpleBannerView, layoutParams)

        //Intercept Status bar height for the correct display of banner
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT_WATCH) {
            decorView.setOnApplyWindowInsetsListener { _, insets ->
                simpleBannerView.translationY = insets.systemWindowInsetTop.toFloat()
                decorView.onApplyWindowInsets(insets)
                return@setOnApplyWindowInsetsListener insets
            }
        }
    }

    override fun onActivityPaused(activity: Activity) {}
    override fun onActivityResumed(activity: Activity) {}
    override fun onActivityStarted(activity: Activity) {}
    override fun onActivityDestroyed(activity: Activity) {}
    override fun onActivitySaveInstanceState(activity: Activity, outState: Bundle?) {}
    override fun onActivityStopped(activity: Activity?) {}

    companion object {
        private lateinit var simpleBanner: SimpleBanner
        fun init(application: Application, @ColorInt color: Int = Color.BLACK, message: String = "") {
            simpleBanner = SimpleBanner(application, color, message)
        }

        fun updateContent(@ColorInt color: Int, message: String) {
            simpleBanner.color = color
            simpleBanner.message = message
        }
    }


}