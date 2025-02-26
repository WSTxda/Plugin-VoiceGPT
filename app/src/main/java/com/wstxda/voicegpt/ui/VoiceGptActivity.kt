package com.wstxda.voicegpt.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import com.wstxda.voicegpt.assistant.AssistantLauncher
import com.wstxda.voicegpt.assistant.DialogManager
import com.wstxda.voicegpt.utils.PreferencesManager

class VoiceGptActivity : ComponentActivity() {

    private val preferencesManager by lazy { PreferencesManager(this) }
    private val assistantLauncher by lazy { AssistantLauncher(this) }
    private val dialogManager by lazy { DialogManager(this, assistantLauncher) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        when (preferencesManager.isDialogShown) {
            false -> dialogManager.showAssistantSetupBottomSheet()
            true -> assistantLauncher.launchAssistant()
        }
    }
}