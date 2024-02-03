package com.wstxda.switchai.assistant

import android.content.ComponentName
import android.content.Intent
import com.wstxda.switchai.R
import com.wstxda.switchai.activity.AssistantActivity
import com.wstxda.switchai.logic.launchAssistant

class ClaudeAssistant : AssistantActivity() {
    override fun onCreateInternal() {
        launchAssistant(
            intents = listOf(createClaudeIntent()),
            errorMessageResId = R.string.assistant_application_not_found,
            packageName = "com.anthropic.claude"
        )
    }

    private fun createClaudeIntent() = Intent().apply {
        component = ComponentName(
            "com.anthropic.claude", "com.anthropic.claude.mainactivity.MainActivity"
        )
    }
}