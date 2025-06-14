package com.wstxda.switchai.assistant

import android.content.ComponentName
import android.content.Intent
import com.wstxda.switchai.R
import com.wstxda.switchai.activity.AssistantActivity
import com.wstxda.switchai.logic.launchAssistant

class WenxinYiyanAssistant : AssistantActivity() {
    override fun onCreateInternal() {
        launchAssistant(
            intents = listOf(createWenxinYiyanIntent()),
            errorMessageResId = R.string.assistant_application_not_found,
            packageName = "com.baidu.newapp"
        )
    }

    private fun createWenxinYiyanIntent() = Intent().apply {
        component = ComponentName(
            "com.baidu.newapp", "com.baidu.newapp.home.MainActivity"
        )
    }
}