package com.example.core.delegtaion

import android.app.Activity
import android.os.Build
import android.view.View
import android.view.WindowInsets

interface SystemUiDelegate {
    fun hideStatusBar(activity: Activity)
    fun showStatusBar(activity: Activity)
}

class SystemUiDelegateImpl : SystemUiDelegate {
    override fun hideStatusBar(activity: Activity) {
        // Hide the status bar
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            activity.window.insetsController?.hide(WindowInsets.Type.statusBars())
        } else {
            @Suppress("DEPRECATION")
            activity.window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN
        }
    }

    override fun showStatusBar(activity: Activity) {
        // Show status bar
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            activity.window.insetsController?.show(WindowInsets.Type.statusBars())
        } else {
            @Suppress("DEPRECATION")
            activity.window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_VISIBLE
        }
    }

}
