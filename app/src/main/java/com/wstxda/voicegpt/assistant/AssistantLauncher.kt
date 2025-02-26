package com.wstxda.voicegpt.assistant

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.widget.Toast
import com.wstxda.voicegpt.R
import com.wstxda.voicegpt.ui.VoiceGptActivity
import com.wstxda.voicegpt.utils.Extensions

class AssistantLauncher(private val context: Context) {

    companion object {
        private const val CHAT_GPT_PACKAGE = "com.openai.chatgpt"
        private const val ASSISTANT_ACTIVITY = "com.openai.voice.assistant.AssistantActivity"
    }

    fun launchAssistant() {
        val intent = Intent(Intent.ACTION_MAIN).apply {
            addCategory(Intent.CATEGORY_LAUNCHER)
            component = ComponentName(CHAT_GPT_PACKAGE, ASSISTANT_ACTIVITY)
        }

        Extensions.launchActivity(context, intent, onSuccess = {
            (context as? VoiceGptActivity)?.finish()
        }, onError = {
            (context as? VoiceGptActivity)?.finish()
            handleAppNotInstalled()
        })
    }

    private fun handleAppNotInstalled() {
        Toast.makeText(context, R.string.chat_gpt_not_found, Toast.LENGTH_SHORT).show()
        openPlayStore()
    }

    private fun openPlayStore() {
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=$CHAT_GPT_PACKAGE"))
        Extensions.launchActivity(context, intent) {
            Toast.makeText(context, R.string.play_store_not_found, Toast.LENGTH_SHORT).show()
        }
    }
}