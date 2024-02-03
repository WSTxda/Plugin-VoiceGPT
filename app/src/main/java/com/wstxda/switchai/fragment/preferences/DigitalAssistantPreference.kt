package com.wstxda.switchai.fragment.preferences

import android.content.Context
import android.os.Build
import androidx.core.content.edit
import androidx.preference.PreferenceFragmentCompat
import androidx.preference.PreferenceManager
import com.wstxda.switchai.utils.Constants

class DigitalAssistantPreference(private val fragment: PreferenceFragmentCompat) {
    private val prefs by lazy { PreferenceManager.getDefaultSharedPreferences(fragment.requireContext()) }

    fun checkDigitalAssistSetupStatus(): Boolean =
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            fragment.requireContext().getSystemService(android.app.role.RoleManager::class.java)
                .isRoleHeld(android.app.role.RoleManager.ROLE_ASSISTANT)
        } else {
            prefs.getBoolean(Constants.IS_ASSIST_SETUP_DONE, false)
        }

    fun setDigitalAssistSetupStatus(context: Context, isSetupDone: Boolean) {
        PreferenceManager.getDefaultSharedPreferences(context).edit {
            putBoolean(Constants.IS_ASSIST_SETUP_DONE, isSetupDone)
        }
    }

    fun updateDigitalAssistantPreferences(isAssistSetupDone: Boolean) {
        fragment.findPreference<androidx.preference.Preference>(Constants.DIGITAL_ASSISTANT_SETUP_PREF_KEY)?.isVisible =
            !isAssistSetupDone
        fragment.findPreference<androidx.preference.ListPreference>(Constants.DIGITAL_ASSISTANT_SELECT_PREF_KEY)
            ?.apply {
                isVisible = isAssistSetupDone
                isEnabled = isAssistSetupDone
            }
    }
}