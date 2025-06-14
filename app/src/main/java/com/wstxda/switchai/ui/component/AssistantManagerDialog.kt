package com.wstxda.switchai.ui.component

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.preference.PreferenceDialogFragmentCompat
import com.wstxda.switchai.R
import com.wstxda.switchai.fragment.preferences.MultiSelectListPreference
import com.wstxda.switchai.utils.Constants

class AssistantManagerDialog : PreferenceDialogFragmentCompat() {

    private lateinit var pref: MultiSelectListPreference
    private val currentSelections = HashSet<String>()
    private var toastShownForInvalidSelection = false

    companion object {
        fun newInstance(key: String) = AssistantManagerDialog().apply {
            arguments = Bundle().apply { putString(ARG_KEY, key) }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        pref = preference as MultiSelectListPreference

        currentSelections.clear()
        val restored = savedInstanceState?.getStringArray(Constants.ASSISTANT_MANAGER_DIALOG)
        if (restored != null) {
            currentSelections.addAll(restored)
        } else {
            currentSelections.addAll(pref.values)
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putStringArray(
            Constants.ASSISTANT_MANAGER_DIALOG, currentSelections.toTypedArray()
        )
    }

    override fun onPrepareDialogBuilder(builder: AlertDialog.Builder) {
        super.onPrepareDialogBuilder(builder)

        val entries = pref.entries
        val entryValues = pref.entryValues
        val checkedItems = BooleanArray(entryValues.size) { index ->
            currentSelections.contains(entryValues[index].toString())
        }

        builder.setMultiChoiceItems(entries, checkedItems) { _, which, isChecked ->
            val value = entryValues[which].toString()
            if (isChecked) currentSelections.add(value) else currentSelections.remove(value)
            updatePositiveButtonState()
        }
    }

    override fun onStart() {
        super.onStart()
        updatePositiveButtonState()
    }

    private fun updatePositiveButtonState() {
        val dialog = dialog as? AlertDialog ?: return
        val okButton = dialog.getButton(AlertDialog.BUTTON_POSITIVE)

        val selectionCount = currentSelections.size
        val valid = selectionCount in pref.minSelection..pref.maxSelection
        okButton.isEnabled = valid

        if (!valid) {
            if (!toastShownForInvalidSelection) {
                toastShownForInvalidSelection = true

                val messageRes = when {
                    selectionCount < pref.minSelection -> R.string.error_min_selection
                    selectionCount > pref.maxSelection -> R.string.error_max_selection
                    else -> 0
                }

                if (messageRes != 0) {
                    Toast.makeText(
                        requireContext(), getString(
                            messageRes,
                            if (selectionCount < pref.minSelection) pref.minSelection else pref.maxSelection
                        ), Toast.LENGTH_SHORT
                    ).show()
                }
            }
        } else {
            toastShownForInvalidSelection = false
        }
    }

    override fun onDialogClosed(positiveResult: Boolean) {
        if (positiveResult) {
            if (pref.callChangeListener(currentSelections)) {
                pref.values = currentSelections
            }
        }
    }
}