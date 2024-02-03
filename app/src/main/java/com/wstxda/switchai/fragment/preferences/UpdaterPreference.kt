package com.wstxda.switchai.fragment.preferences

import android.content.Context
import android.util.AttributeSet
import android.widget.Button
import android.widget.TextView
import androidx.preference.Preference
import androidx.preference.PreferenceViewHolder
import com.wstxda.switchai.R
import com.wstxda.switchai.services.UpdaterService
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class UpdaterPreference @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : Preference(context, attrs, defStyleAttr) {

    init {
        layoutResource = R.layout.preference_material_updater
    }

    override fun onBindViewHolder(holder: PreferenceViewHolder) {
        super.onBindViewHolder(holder)
        holder.itemView.apply {
            isClickable = false
            isFocusable = false
        }
        (holder.findViewById(android.R.id.summary) as? TextView)?.text = getVersionName(context)

        (holder.findViewById(R.id.check_updates) as? Button)?.setOnClickListener {
            CoroutineScope(Dispatchers.Main).launch {
                UpdaterService.checkForUpdates(context, holder.itemView)
            }
        }
    }

    private fun getVersionName(context: Context): String = runCatching {
        context.packageManager.getPackageInfo(context.packageName, 0).versionName ?: "N/A"
    }.getOrDefault("N/A")
}