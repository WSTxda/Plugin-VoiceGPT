package com.wstxda.switchai.fragment.preferences

import android.annotation.SuppressLint
import android.content.Context
import androidx.core.content.ContextCompat
import androidx.preference.ListPreference
import com.wstxda.switchai.R

class AssistantIconUpdater {

    @SuppressLint("DiscouragedApi")
    fun getAssistantIconResId(context: Context, assistantValue: String?): Int {
        if (assistantValue.isNullOrEmpty()) return R.drawable.ic_assistant_default

        val resourceName = "ic_assistant_" + assistantValue.replace("_assistant", "")
        val resId = context.resources.getIdentifier(resourceName, "drawable", context.packageName)

        return if (resId != 0) resId else R.drawable.ic_assistant_default
    }

    fun updateIcon(context: Context, preference: ListPreference?, assistantValue: String?) {
        if (preference == null) return

        val drawableId = getAssistantIconResId(context, assistantValue)
        val drawable = ContextCompat.getDrawable(context, drawableId)
        preference.icon = drawable
    }
}