package com.wstxda.voicegpt.utils

import android.content.Context
import android.content.SharedPreferences
import androidx.core.content.edit

class PreferencesManager(context: Context) {
    private val prefs: SharedPreferences =
        context.getSharedPreferences(PREFERENCES_NAME, Context.MODE_PRIVATE)

    var isDialogShown: Boolean
        get() = prefs.getBoolean(DIALOG_SHOWN_KEY, false)
        set(value) = prefs.edit { putBoolean(DIALOG_SHOWN_KEY, value) }

    companion object {
        private const val PREFERENCES_NAME = "VoiceGptPrefs"
        private const val DIALOG_SHOWN_KEY = "dialogShown"
    }
}