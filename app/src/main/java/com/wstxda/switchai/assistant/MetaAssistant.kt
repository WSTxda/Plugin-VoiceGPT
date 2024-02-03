package com.wstxda.switchai.assistant

import  android.content.ComponentName
import android.content.Intent
import com.wstxda.switchai.R
import com.wstxda.switchai.activity.AssistantActivity
import com.wstxda.switchai.logic.launchAssistant

class MetaAssistant : AssistantActivity() {
    override fun onCreateInternal() {
        launchAssistant(
            intents = listOf(createMetaIntent()),
            errorMessageResId = R.string.assistant_application_not_found,
            packageName = "com.facebook.stella"
        )
    }

    private fun createMetaIntent() = Intent().apply {
        component = ComponentName(
            "com.facebook.stella", "com.facebook.stella.main.view.MainActivity"
        )
    }
}