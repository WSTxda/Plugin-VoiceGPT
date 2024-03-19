package com.wstxda.voicegpt

import android.app.Activity
import android.app.AlertDialog
import android.content.ActivityNotFoundException
import android.content.ComponentName
import android.content.Intent
import android.content.SharedPreferences
import android.net.Uri
import android.os.Bundle
import android.provider.Settings
import android.widget.Toast

class VoiceGptActivity : Activity() {

    private lateinit var sharedPreferences: SharedPreferences

    companion object {
        const val CHAT_GPT_PACKAGE = "com.openai.chatgpt"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sharedPreferences = getSharedPreferences("VoiceGptPrefs", MODE_PRIVATE)
        if (!sharedPreferences.getBoolean("dialogShown", false)) {
            showVoiceAssistantDialog()
        } else {
            openAssistantActivity()
        }
    }

    private fun showVoiceAssistantDialog() {
        AlertDialog.Builder(this)
            .setTitle(R.string.assistant_dialog_title)
            .setMessage(R.string.assistant_dialog_summary)
            .setPositiveButton(R.string.assistant_dialog_setup) { _, _ ->
                setAppAsVoiceAssistant()
            }.setNegativeButton(R.string.assistant_dialog_cancel) { dialog, _ ->
                openAssistantActivity()
                dialog.dismiss()
                sharedPreferences.edit().putBoolean("dialogShown", true).apply()
            }.setOnCancelListener {
                finish()
            }.create().show()
    }

    private fun setAppAsVoiceAssistant() {
        sharedPreferences.edit().putBoolean("dialogShown", true).apply()
        startActivity(Intent(Settings.ACTION_VOICE_INPUT_SETTINGS))
        finish()
    }

    private fun openAssistantActivity() {
        val intent = Intent().apply {
            action = Intent.ACTION_MAIN
            addCategory(Intent.CATEGORY_LAUNCHER)
            component =
                ComponentName(CHAT_GPT_PACKAGE, "com.openai.voice.assistant.AssistantActivity")
        }
        try {
            startActivity(intent)
            finish()
        } catch (e: ActivityNotFoundException) {
            Toast.makeText(applicationContext, R.string.voice_gpt_not_found, Toast.LENGTH_SHORT)
                .show()
            openPlayStore()
            finish()
        }
    }

    private fun openPlayStore() {
        try {
            val intent =
                Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=$CHAT_GPT_PACKAGE"))
            startActivity(intent)
        } catch (e: ActivityNotFoundException) {
            val intent = Intent(
                Intent.ACTION_VIEW,
                Uri.parse("https://play.google.com/store/apps/details?id=$CHAT_GPT_PACKAGE")
            )
            startActivity(intent)
        }
    }
}