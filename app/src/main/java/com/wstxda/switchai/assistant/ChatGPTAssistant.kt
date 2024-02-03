package com.wstxda.switchai.assistant

import android.content.ComponentName
import android.content.Intent
import com.wstxda.switchai.R
import com.wstxda.switchai.activity.AssistantActivity
import com.wstxda.switchai.logic.launchAssistant

class ChatGPTAssistant : AssistantActivity() {
    override fun onCreateInternal() {
        launchAssistant(
            intents = listOf(createChatGPTIntent()),
            errorMessageResId = R.string.assistant_application_not_found,
            packageName = "com.openai.chatgpt"
        )
    }

    private fun createChatGPTIntent() = Intent().apply {
        component = ComponentName(
            "com.openai.chatgpt", "com.openai.voice.assistant.AssistantActivity"
        )
    }
}