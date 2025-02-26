package com.wstxda.voicegpt.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import com.wstxda.voicegpt.assistant.ActivityLauncher
import com.wstxda.voicegpt.assistant.DialogManager
import com.wstxda.voicegpt.utils.PreferencesManager

class VoiceGptActivity : ComponentActivity() {

    private val preferencesManager by lazy { PreferencesManager(this) }
    private val activityLauncher by lazy { ActivityLauncher(this) }
    private val dialogManager by lazy { DialogManager(this, activityLauncher) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        when (preferencesManager.isDialogShown) {
            false -> dialogManager.showAssistantSetupBottomSheet()
            true -> activityLauncher.launchAssistant()
        }
    }
}