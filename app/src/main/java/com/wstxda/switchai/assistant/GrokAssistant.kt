package com.wstxda.switchai.assistant

import android.content.ComponentName
import android.content.Intent
import com.wstxda.switchai.R
import com.wstxda.switchai.activity.AssistantActivity
import com.wstxda.switchai.logic.launchAssistant

class GrokAssistant : AssistantActivity() {
    override fun onCreateInternal() {
        launchAssistant(
            intents = listOf(createGrokIntent()),
            errorMessageResId = R.string.assistant_application_not_found,
            packageName = "ai.x.grok"
        )
    }

    private fun createGrokIntent() = Intent().apply {
        component = ComponentName(
            "ai.x.grok", "ai.x.grok.main.GrokActivity"
        )
    }
}