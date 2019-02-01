package com.offbow.simplebanner

import android.app.Activity
import android.app.Application
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.view.ViewGroup
import java.lang.ref.WeakReference

class SimpleBanner(private val application: Application,
                   private var color: Int,
                   private var message: String) : Application.ActivityLifecycleCallbacks {

    init {
        application.registerActivityLifecycleCallbacks(this)
    }

    private var currentActivity: WeakReference<Activity?> = WeakReference(null)

    override fun onActivityCreated(activity: Activity, savedInstanceState: Bundle?) {
        currentActivity = WeakReference(activity)
        currentActivity.get()?.let {
            drawBanner(it)
        }
    }

    private fun updateBannerContent(color: Int, message: String) {
        this.color = color
        this.message = message
        currentActivity.get()?.let {
            drawBanner(it)
        }
    }

    private fun drawBanner(activity: Activity) {
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

    private fun destroy(){
        application.unregisterActivityLifecycleCallbacks(this)
        currentActivity.clear()
    }

    companion object {
        private lateinit var simpleBanner: SimpleBanner
        fun init(application: Application, color: Int = Color.BLACK, message: String = "") {
            simpleBanner = SimpleBanner(application, color, message)
        }

        fun updateContent(color: Int, message: String) {
            simpleBanner.updateBannerContent(color, message)
        }

        fun destroy() {
            simpleBanner.destroy()
        }
    }

}