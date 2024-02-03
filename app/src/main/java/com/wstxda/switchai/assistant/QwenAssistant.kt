package com.wstxda.switchai.assistant

import android.content.ComponentName
import android.content.Intent
import com.wstxda.switchai.R
import com.wstxda.switchai.activity.AssistantActivity
import com.wstxda.switchai.logic.launchAssistant

class QwenAssistant : AssistantActivity() {
    override fun onCreateInternal() {
        launchAssistant(
            intents = listOf(createQwenIntent()),
            errorMessageResId = R.string.assistant_application_not_found,
            packageName = "ai.qwenlm.chat.android"
        )
    }

    private fun createQwenIntent() = Intent().apply {
        component = ComponentName(
            "ai.qwenlm.chat.android", "ai.qwenlm.chat.android.MainActivity"
        )
    }
}