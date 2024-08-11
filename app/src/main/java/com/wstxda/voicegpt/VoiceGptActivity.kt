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
        private const val CHAT_GPT_PACKAGE = "com.openai.chatgpt"
        private const val PREFERENCES_NAME = "VoiceGptPrefs"
        private const val DIALOG_SHOWN_KEY = "dialogShown"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sharedPreferences = getSharedPreferences(PREFERENCES_NAME, MODE_PRIVATE)
        if (isFirstTimeLaunch()) {
            showVoiceAssistantDialog()
        } else {
            launchAssistantActivity()
        }
    }

    private fun isFirstTimeLaunch() = !sharedPreferences.getBoolean(DIALOG_SHOWN_KEY, false)

    private fun showVoiceAssistantDialog() {
        AlertDialog.Builder(this)
            .setTitle(R.string.assistant_dialog_title)
            .setMessage(R.string.assistant_dialog_summary)
            .setPositiveButton(R.string.assistant_dialog_setup) { _, _ ->
                markDialogAsShown()
                openVoiceInputSettings()
            }.setNegativeButton(R.string.assistant_dialog_cancel) { dialog, _ ->
                markDialogAsShown()
                launchAssistantActivity()
                dialog.dismiss()
            }.setOnCancelListener {
                finish()
            }.create().show()
    }

    private fun markDialogAsShown() {
        sharedPreferences.edit().putBoolean(DIALOG_SHOWN_KEY, true).apply()
    }

    private fun openVoiceInputSettings() {
        startActivity(Intent(Settings.ACTION_VOICE_INPUT_SETTINGS))
    }

    private fun launchAssistantActivity() {
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
            handleAssistantAppNotInstalled()
        }
    }

    private fun handleAssistantAppNotInstalled() {
        Toast.makeText(applicationContext, R.string.chat_gpt_not_found, Toast.LENGTH_SHORT).show()
        openPlayStoreListing()
        finish()
    }

    private fun openPlayStoreListing() {
        val playStoreIntent = Intent(Intent.ACTION_VIEW).apply {
            data = Uri.parse("market://details?id=$CHAT_GPT_PACKAGE")
        }
        if (playStoreIntent.resolveActivity(packageManager) != null) {
            startActivity(playStoreIntent)
        } else {
            val webIntent = Intent(Intent.ACTION_VIEW).apply {
                data = Uri.parse("https://play.google.com/store/apps/details?id=$CHAT_GPT_PACKAGE")
            }
            startActivity(webIntent)
        }
    }
}
