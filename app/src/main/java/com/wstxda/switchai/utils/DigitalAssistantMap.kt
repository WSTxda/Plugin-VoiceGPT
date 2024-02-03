package com.wstxda.switchai.utils

import android.content.Context
import android.content.Intent
import com.wstxda.switchai.assistant.*
import com.wstxda.switchai.logic.PreferenceHelper
import com.wstxda.switchai.logic.launchAssistant

object DigitalAssistantMap {
    internal val assistantsMap = mapOf(
        "chatgpt_assistant" to ChatGPTAssistant::class.java,
        "claude_assistant" to ClaudeAssistant::class.java,
        "copilot_assistant" to CopilotAssistant::class.java,
        "deepseek_assistant" to DeepSeekAssistant::class.java,
        "gemini_assistant" to GeminiAssistant::class.java,
        "grok_assistant" to GrokAssistant::class.java,
        "le_chat_assistant" to LeChatAssistant::class.java,
        "kimi_assistant" to KimiAssistant::class.java,
        "manus_assistant" to ManusAssistant::class.java,
        "meta_assistant" to MetaAssistant::class.java,
        "perplexity_assistant" to PerplexityAssistant::class.java,
        "qwen_assistant" to QwenAssistant::class.java,
        "ultimate_alexa_assistant" to UltimateAlexaAssistant::class.java
    )

    fun launchSelectedAssistant(context: Context) {
        val preferenceHelper = PreferenceHelper(context)
        val selectedShortcut =
            preferenceHelper.getString(Constants.DIGITAL_ASSISTANT_SELECT_PREF_KEY, null) ?: return
        val activityClass = assistantsMap[selectedShortcut] ?: return
        val intent = Intent(context, activityClass)
        context.launchAssistant(intent)
    }
}