package com.example.data.util

import android.content.SharedPreferences
import com.google.gson.Gson

class SettingsPref(
    private val prefs: SharedPreferences,
) {
    private val gson: Gson = Gson()

    fun saveToken(token: String) =
        prefs.edit().putString(TOKEN_KEY, token).apply()


    fun getToken(): String? = prefs.getString(TOKEN_KEY, null)

    fun deleteToken() = prefs.edit().putString(TOKEN_KEY, null).apply()

    fun setFirstLaunch() =
        prefs.edit().putBoolean(FIRST_LAUNCH_KEY, false).apply()

    fun isFirstLaunch(): Boolean = prefs.getBoolean(FIRST_LAUNCH_KEY, true)
}