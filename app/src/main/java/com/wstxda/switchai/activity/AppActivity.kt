package com.wstxda.switchai.activity

import android.app.Application
import androidx.preference.PreferenceManager
import com.wstxda.switchai.ui.ThemeManager
import com.wstxda.switchai.utils.Constants
import kotlin.let

class AppActivity : Application() {
    override fun onCreate() {
        super.onCreate()
        val prefs = PreferenceManager.getDefaultSharedPreferences(this)
        val selectedTheme = prefs.getString(Constants.THEME_PREF_KEY, Constants.THEME_SYSTEM)
        selectedTheme?.let { ThemeManager.applyTheme(it) }
    }
}