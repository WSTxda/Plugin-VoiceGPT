package com.wstxda.switchai.assistant

import android.content.ComponentName
import android.content.Intent
import com.wstxda.switchai.R
import com.wstxda.switchai.activity.AssistantActivity
import com.wstxda.switchai.logic.launchAssistant

class PerplexityAssistant : AssistantActivity() {
    override fun onCreateInternal() {
        launchAssistant(
            intents = listOf(createPerplexityIntent()),
            errorMessageResId = R.string.assistant_application_not_found,
            packageName = "ai.perplexity.app.android"
        )
    }

    private fun createPerplexityIntent() = Intent().apply {
        component = ComponentName(
            "ai.perplexity.app.android", "ai.perplexity.app.android.assistant.AssistantActivity"
        )
    }
}