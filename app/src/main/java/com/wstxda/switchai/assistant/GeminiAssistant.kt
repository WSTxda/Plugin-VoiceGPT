package com.wstxda.switchai.assistant

import android.content.ComponentName
import android.content.Intent
import com.wstxda.switchai.R
import com.wstxda.switchai.activity.AssistantActivity
import com.wstxda.switchai.logic.launchAssistant

class GeminiAssistant : AssistantActivity() {
    override fun onCreateInternal() {
        launchAssistant(
            intents = listOf(createGeminiIntent()),
            errorMessageResId = R.string.assistant_application_not_found,
            packageName = "com.google.android.apps.bard"
        )
    }

    private fun createGeminiIntent() = Intent().apply {
        component = ComponentName(
            "com.google.android.apps.bard",
            "com.google.android.apps.bard.shellapp.BardEntryPointActivity"
        )
    }
}