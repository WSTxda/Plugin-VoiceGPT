package com.wstxda.switchai.assistant

import android.content.ComponentName
import android.content.Intent
import com.wstxda.switchai.R
import com.wstxda.switchai.activity.AssistantActivity
import com.wstxda.switchai.logic.launchAssistant

class QingyanAssistant : AssistantActivity() {
    override fun onCreateInternal() {
        launchAssistant(
            intents = listOf(createQingyanIntent()),
            errorMessageResId = R.string.assistant_application_not_found,
            packageName = "com.zhipuai.qingyan"
        )
    }

    private fun createQingyanIntent() = Intent().apply {
        component = ComponentName(
            "com.zhipuai.qingyan", "com.zhipuai.qingyan.MainActivity"
        )
    }
}