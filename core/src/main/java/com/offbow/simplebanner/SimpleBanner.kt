package com.offbow.simplebanner

import android.app.Activity
import android.app.Application
import android.graphics.Color
import android.graphics.Rect
import android.os.Build
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.view.Window
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
            val rect = Rect()
            decorView.getWindowVisibleDisplayFrame(rect)
            activity.window.findViewById<View>(Window.ID_ANDROID_CONTENT)?.apply {
                post {
                    //The top of the displayable view minus the top for the contents view
                    simpleBannerView.translationY = (this.top - rect.top).toFloat()
                }
            }
        }
    }

    override fun onActivityPaused(activity: Activity) {}
    override fun onActivityResumed(activity: Activity) {}
    override fun onActivityStarted(activity: Activity) {}
    override fun onActivityDestroyed(activity: Activity) {}
    override fun onActivitySaveInstanceState(activity: Activity, outState: Bundle?) {}
    override fun onActivityStopped(activity: Activity?) {}

    private fun destroy() {
        application.unregisterActivityLifecycleCallbacks(this)
        currentActivity.clear()
    }

    companion object {
        private lateinit var simpleBanner: SimpleBanner

        /**
         * Setups the Simple Banner library
         *
         * @param application The Application for the library to attach to.
         * @param color An Int colour (Default Black).
         * @param message The message to display (Default "")
         */
        fun init(application: Application, color: Int = Color.BLACK, message: String = "") {
            simpleBanner = SimpleBanner(application, color, message)
        }

        /**
         * Allows the banner color and message to be updated
         *
         * @param color An Int colour (Default Black).
         * @param message The message to display (Default "")
         */
        fun updateContent(color: Int = simpleBanner.color, message: String = simpleBanner.message) {
            simpleBanner.updateBannerContent(color, message)
        }

        /**
         * Call to destroy the library
         */
        fun destroy() {
            simpleBanner.destroy()
        }
    }

}