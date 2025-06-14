package com.wstxda.switchai.assistant

import android.content.ComponentName
import android.content.Intent
import com.wstxda.switchai.R
import com.wstxda.switchai.activity.AssistantActivity
import com.wstxda.switchai.logic.launchAssistant

class AlexaAssistant : AssistantActivity() {
    override fun onCreateInternal() {
        launchAssistant(
            intents = listOf(createAlexaIntent()),
            errorMessageResId = R.string.assistant_application_not_found,
            packageName = "com.amazon.dee.app"
        )
    }

    private fun createAlexaIntent() = Intent().apply {
        component = ComponentName(
            "com.amazon.dee.app", "com.amazon.alexa.voice.ui.VoiceActivity"
        )
    }
}