package com.wstxda.switchai.assistant

import android.content.ComponentName
import android.content.Intent
import com.wstxda.switchai.R
import com.wstxda.switchai.activity.AssistantActivity
import com.wstxda.switchai.logic.launchAssistant

class ManusAssistant : AssistantActivity() {
    override fun onCreateInternal() {
        launchAssistant(
            intents = listOf(createManusIntent()),
            errorMessageResId = R.string.assistant_application_not_found,
            packageName = "tech.butterfly.app"
        )
    }

    private fun createManusIntent() = Intent().apply {
        component = ComponentName(
            "tech.butterfly.app", "tech.butterfly.app.MainActivity"
        )
    }
}