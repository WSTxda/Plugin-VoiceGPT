package com.wstxda.switchai.ui

import androidx.appcompat.app.AppCompatDelegate
import com.wstxda.switchai.utils.Constants

object ThemeManager {

    fun applyTheme(theme: String) {
        AppCompatDelegate.setDefaultNightMode(
            when (theme) {
                Constants.THEME_LIGHT -> AppCompatDelegate.MODE_NIGHT_NO
                Constants.THEME_DARK -> AppCompatDelegate.MODE_NIGHT_YES
                Constants.THEME_SYSTEM -> AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM
                else -> AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM
            }
        )
    }
}