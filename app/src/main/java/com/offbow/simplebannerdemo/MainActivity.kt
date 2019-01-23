package com.offbow.simplebannerdemo

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.offbow.simplebanner.SimpleBanner
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        main_forward.setOnClickListener {
            Intent(this, SecondActivity::class.java)
                    .apply {
                        startActivity(this)
                    }
        }
    }

    override fun onResume() {
        super.onResume()
        SimpleBanner.updateContent(Color.GREEN, "Build #${BuildConfig.VERSION_CODE}, BuildType ${BuildConfig.BUILD_TYPE}")
    }
}
