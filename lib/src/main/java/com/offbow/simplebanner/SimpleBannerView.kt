package com.offbow.simplebanner

import android.content.Context
import android.graphics.Color
import android.graphics.Typeface
import android.util.AttributeSet
import android.widget.FrameLayout
import android.widget.TextView
import android.util.TypedValue
import android.view.Gravity

internal class SimpleBannerView(context: Context, attrs: AttributeSet? = null) : FrameLayout(context, attrs) {

    private val bannerTextView = TextView(context)
    private var textViewMargins = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 5f, resources.displayMetrics).toInt()

    init {
        isClickable = false

        bannerTextView.text = "This is a test TODO"
        bannerTextView.setTextColor(Color.BLACK)
        bannerTextView.typeface = Typeface.DEFAULT_BOLD

        FrameLayout.LayoutParams(FrameLayout.LayoutParams.WRAP_CONTENT, FrameLayout.LayoutParams.WRAP_CONTENT, Gravity.CENTER).apply {
            setMargins(textViewMargins, 0, textViewMargins, 0)
            this@SimpleBannerView.addView(bannerTextView, this)
        }
    }
}
