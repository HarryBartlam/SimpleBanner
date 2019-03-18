package com.offbow.simplebanner

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Typeface
import android.widget.FrameLayout
import android.widget.TextView
import android.util.TypedValue
import android.view.Gravity

@SuppressLint("ViewConstructor")//
internal class SimpleBannerView(context: Context,
                                color: Int,
                                message: String) : FrameLayout(context) {

    private val bannerTextView = TextView(context)
    private val textViewMargins = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 5f, resources.displayMetrics).toInt()

    init {
        isClickable = false

        bannerTextView.text = message
        bannerTextView.setTextColor(color)
        bannerTextView.typeface = Typeface.DEFAULT_BOLD

        FrameLayout.LayoutParams(FrameLayout.LayoutParams.WRAP_CONTENT, FrameLayout.LayoutParams.WRAP_CONTENT, Gravity.CENTER).apply {
            setMargins(textViewMargins, 0, textViewMargins, 0)
            this@SimpleBannerView.addView(bannerTextView, this)
        }
    }
}
