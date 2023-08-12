package com.example.core.data

import android.util.Log

fun Any?.printToLog(tag: String = "DEBUG_LOG") {
    Log.d(tag, toString())
}