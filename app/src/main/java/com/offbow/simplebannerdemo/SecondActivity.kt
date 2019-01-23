package com.offbow.simplebannerdemo

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.offbow.simplebanner.SimpleBanner
import kotlinx.android.synthetic.main.activity_second.*

class SecondActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)
        second_return.setOnClickListener {
            finish()
        }
    }

    override fun onResume() {
        super.onResume()
        SimpleBanner.updateContent(Color.BLACK, "This is a demo of the content changing")
    }
}
