package com.wstxda.switchai.assistant

import android.content.ComponentName
import android.content.Intent
import com.wstxda.switchai.R
import com.wstxda.switchai.activity.AssistantActivity
import com.wstxda.switchai.logic.launchAssistant

class CopilotAssistant : AssistantActivity() {
    override fun onCreateInternal() {
        launchAssistant(
            intents = listOf(createCopilotIntent()),
            errorMessageResId = R.string.assistant_application_not_found,
            packageName = "com.microsoft.copilot"
        )
    }

    private fun createCopilotIntent() = Intent().apply {
        component = ComponentName(
            "com.microsoft.copilot",
            "com.microsoft.copilotn.features.digitalassistant.AssistantOverlayActivity"
        )
    }
}