package com.wstxda.switchai.assistant

import android.content.ComponentName
import android.content.Intent
import com.wstxda.switchai.R
import com.wstxda.switchai.activity.AssistantActivity
import com.wstxda.switchai.logic.launchAssistant

class LeChatAssistant : AssistantActivity() {
    override fun onCreateInternal() {
        launchAssistant(
            intents = listOf(createLeChatIntent()),
            errorMessageResId = R.string.assistant_application_not_found,
            packageName = "ai.mistral.chat"
        )
    }

    private fun createLeChatIntent() = Intent().apply {
        component = ComponentName(
            "ai.mistral.chat", "ai.mistral.chat.MainActivity"
        )
    }
}