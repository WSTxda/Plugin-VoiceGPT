package com.wstxda.voicegpt.utils

import android.content.Context
import android.content.Intent
import android.widget.Toast
import com.wstxda.voicegpt.R

object Extensions {
    fun launchActivity(
        context: Context, intent: Intent, onSuccess: (() -> Unit)? = null, onError: () -> Unit = {}
    ) {
        if (intent.resolveActivity(context.packageManager) != null) {
            context.startActivity(intent)
            onSuccess?.invoke()
        } else {
            onError()
            Toast.makeText(context, R.string.chat_gpt_not_found, Toast.LENGTH_SHORT).show()
        }
    }
}