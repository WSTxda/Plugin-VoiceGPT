package com.wstxda.switchai.assistant

import android.content.ComponentName
import android.content.Intent
import com.wstxda.switchai.R
import com.wstxda.switchai.activity.AssistantActivity
import com.wstxda.switchai.logic.launchAssistant

class DoubaoAssistant : AssistantActivity() {
    override fun onCreateInternal() {
        launchAssistant(
            intents = listOf(createAlexaIntent()),
            errorMessageResId = R.string.assistant_application_not_found,
            packageName = "com.larus.nova"
        )
    }

    private fun createAlexaIntent() = Intent().apply {
        component = ComponentName(
            "com.larus.nova", "com.larus.home.impl.MainActivity"
        )
    }
}