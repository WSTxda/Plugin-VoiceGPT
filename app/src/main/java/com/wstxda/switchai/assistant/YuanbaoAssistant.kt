package com.wstxda.switchai.assistant

import android.content.ComponentName
import android.content.Intent
import com.wstxda.switchai.R
import com.wstxda.switchai.activity.AssistantActivity
import com.wstxda.switchai.logic.launchAssistant

class YuanbaoAssistant : AssistantActivity() {
    override fun onCreateInternal() {
        launchAssistant(
            intents = listOf(createYuanbaoIntent()),
            errorMessageResId = R.string.assistant_application_not_found,
            packageName = "com.tencent.hunyuan.app.chat"
        )
    }

    private fun createYuanbaoIntent() = Intent().apply {
        component = ComponentName(
            "com.tencent.hunyuan.app.chat", "com.tencent.hunyuan.app.chat.biz.login.v2.HYLoginMainActivity"
        )
    }
}