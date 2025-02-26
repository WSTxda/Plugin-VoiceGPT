package com.wstxda.voicegpt.assistant

import android.content.Context
import android.content.Intent
import android.provider.Settings
import android.widget.Toast
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.wstxda.voicegpt.R
import com.wstxda.voicegpt.databinding.BottomSheetAssistantBinding
import com.wstxda.voicegpt.utils.PreferencesManager
import com.wstxda.voicegpt.ui.VoiceGptActivity
import com.wstxda.voicegpt.utils.Extensions

class DialogManager(
    private val context: Context, private val assistantLauncher: AssistantLauncher
) {
    private val preferencesManager = PreferencesManager(context)

    fun showAssistantSetupBottomSheet() {
        val dialog = BottomSheetDialog(context)
        val binding = BottomSheetAssistantBinding.inflate(dialog.layoutInflater)

        with(binding) {
            setupButton.setOnClickListener {
                preferencesManager.isDialogShown = true
                openVoiceInputSettings()
                dialog.dismiss()
            }
            cancelButton.setOnClickListener {
                preferencesManager.isDialogShown = true
                assistantLauncher.launchAssistant()
                dialog.dismiss()
            }
        }

        dialog.setContentView(binding.root)
        dialog.setOnCancelListener { finishActivity() }
        dialog.show()
    }

    private fun openVoiceInputSettings() {
        val intent = Intent(Settings.ACTION_VOICE_INPUT_SETTINGS)
        Extensions.launchActivity(context, intent, onError = {
            Toast.makeText(
                context, R.string.voice_input_not_available, Toast.LENGTH_SHORT
            ).show()
        })
    }

    private fun finishActivity() {
        if (context is VoiceGptActivity) {
            context.finish()
        }
    }
}