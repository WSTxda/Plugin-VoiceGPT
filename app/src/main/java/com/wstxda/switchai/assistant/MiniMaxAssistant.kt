package com.wstxda.switchai.assistant

import android.content.ComponentName
import android.content.Intent
import com.wstxda.switchai.R
import com.wstxda.switchai.activity.AssistantActivity
import com.wstxda.switchai.logic.launchAssistant

class MiniMaxAssistant : AssistantActivity() {
    override fun onCreateInternal() {
        launchAssistant(
            intents = listOf(createMiniMaxIntent()),
            errorMessageResId = R.string.assistant_application_not_found,
            packageName = "com.minimax.ai"
        )
    }

    private fun createMiniMaxIntent() = Intent().apply {
        component = ComponentName(
            "com.minimax.ai", "com.xproducer.yingshi.app.MainSplashActivity"
        )
    }
}