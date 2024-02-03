package com.wstxda.voicegpt

import android.app.Activity
import android.content.ComponentName
import android.content.Intent
import android.widget.Toast

class VoiceGptActivity : Activity() {
    public override fun onResume() {
        super.onResume()
        try {
            val intent = Intent("android.intent.action.MAIN")
            intent.addCategory("android.intent.category.LAUNCHER")
            intent.component = ComponentName(
                "com.openai.chatgpt", "com.openai.voice.assistant.AssistantActivity"
            )
            finish()

            startActivity(intent)
        } catch (unused: Exception) {
            Toast.makeText(applicationContext, R.string.voice_gpt_not_found, Toast.LENGTH_SHORT)
                .show()
            finish()
        }
    }
}