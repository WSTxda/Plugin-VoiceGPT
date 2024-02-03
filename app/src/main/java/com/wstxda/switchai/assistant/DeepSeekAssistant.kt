package com.wstxda.switchai.assistant

import android.content.ComponentName
import android.content.Intent
import com.wstxda.switchai.R
import com.wstxda.switchai.activity.AssistantActivity
import com.wstxda.switchai.logic.launchAssistant

class DeepSeekAssistant : AssistantActivity() {
    override fun onCreateInternal() {
        launchAssistant(
            intents = listOf(createDeepSeekIntent()),
            errorMessageResId = R.string.assistant_application_not_found,
            packageName = "com.deepseek.chat"
        )
    }

    private fun createDeepSeekIntent() = Intent().apply {
        component = ComponentName(
            "com.deepseek.chat", "com.deepseek.chat.MainActivity"
        )
    }
}