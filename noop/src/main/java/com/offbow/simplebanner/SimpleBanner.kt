package com.offbow.simplebanner

import android.app.Application
import android.graphics.Color

class SimpleBanner()  {
    companion object {

        /**
         * Setups the Simple Banner library
         *
         * @param application The Application for the library to attach to.
         * @param color An Int colour (Default Black).
         * @param message The message to display (Default "")
         */
        fun init(application: Application, color: Int = Color.BLACK, message: String = "") {
        }

        /**
         * Allows the banner color and message to be updated
         *
         * @param color An Int colour (Default Black).
         * @param message The message to display (Default "")
         */
        fun updateContent(color: Int = 0, message: String = "") {
        }

        /**
         * Call to destroy the library
         */
        fun destroy() {
        }
    }

}