package com.wstxda.switchai.assistant

import android.content.ComponentName
import android.content.Intent
import com.wstxda.switchai.R
import com.wstxda.switchai.activity.AssistantActivity
import com.wstxda.switchai.logic.launchAssistant

class UltimateAlexaAssistant : AssistantActivity() {
    override fun onCreateInternal() {
        launchAssistant(
            intents = listOf(createUltimateAlexaIntent()),
            errorMessageResId = R.string.assistant_application_not_found,
            packageName = "com.customsolutions.android.alexa"
        )
    }

    private fun createUltimateAlexaIntent() = Intent().apply {
        component = ComponentName(
            "com.customsolutions.android.alexa", "com.customsolutions.android.alexa.MainActivity"
        )
    }
}