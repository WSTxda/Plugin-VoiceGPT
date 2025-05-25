package com.wstxda.switchai.fragment

import android.content.Intent
import android.os.Build
import android.os.Bundle
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.net.toUri
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.preference.ListPreference
import androidx.preference.Preference
import androidx.preference.PreferenceCategory
import androidx.preference.PreferenceFragmentCompat
import com.wstxda.switchai.R
import com.wstxda.switchai.activity.LibraryActivity
import com.wstxda.switchai.fragment.preferences.DigitalAssistantPreference
import com.wstxda.switchai.ui.TileManager
import com.wstxda.switchai.ui.component.DigitalAssistantSetupDialog
import com.wstxda.switchai.utils.Constants
import com.wstxda.switchai.viewmodel.SettingsViewModel
import kotlinx.coroutines.launch
import kotlin.getValue

class SettingsFragment : PreferenceFragmentCompat() {

    private val viewModel: SettingsViewModel by viewModels {
        ViewModelProvider.AndroidViewModelFactory.getInstance(requireActivity().application)
    }
    private val digitalAssistantPreference by lazy { DigitalAssistantPreference(this) }
    private val digitalAssistantLauncher = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) {
        viewLifecycleOwner.lifecycleScope.launch {
            val isDone = digitalAssistantPreference.checkDigitalAssistSetupStatus()
            viewModel.setAssistSetupDone(isDone)
            digitalAssistantPreference.updateDigitalAssistantPreferences(isDone)
            if (!isDone) setupDigitalAssistantClickListener()
        }
    }

    private val links = mapOf(
        "developer" to "https://github.com/WSTxda",
        "github_repository" to "https://github.com/WSTxda/SwitchAI",
    )

    private fun observeViewModel() {
        viewModel.isAssistSetupDone.observe(this) { isDone ->
            findPreference<Preference>(Constants.DIGITAL_ASSISTANT_SETUP_PREF_KEY)?.isVisible = !isDone
        }
    }

    override fun onResume() {
        super.onResume()
        viewLifecycleOwner.lifecycleScope.launch {
            val isDone = digitalAssistantPreference.checkDigitalAssistSetupStatus()
            viewModel.setAssistSetupDone(isDone)
            digitalAssistantPreference.updateDigitalAssistantPreferences(isDone)
        }
    }

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.main_preferences, rootKey)
        setupInitialVisibility()
        setupPreferences()
        observeViewModel()
    }

    private fun setupPreferences() {
        setupDigitalAssistantClickListener()
        setupThemePreference()
        setupTilePreference()
        setupLibraryPreference()
        setupLinkPreferences()
    }

    private fun setupInitialVisibility() {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.TIRAMISU) {
            findPreference<Preference>(Constants.DIGITAL_ASSISTANT_TILE_PREF_KEY)?.isVisible = false
            findPreference<PreferenceCategory>(Constants.SETTINGS_CATEGORY_SHORTCUTS)?.isVisible = false
        }
    }

    private fun setupDigitalAssistantClickListener() {
        findPreference<Preference>(Constants.DIGITAL_ASSISTANT_SETUP_PREF_KEY)?.setOnPreferenceClickListener {
            DigitalAssistantSetupDialog.show(childFragmentManager, digitalAssistantLauncher)
            true
        }
    }

    private fun setupThemePreference() {
        findPreference<ListPreference>(Constants.THEME_PREF_KEY)?.setOnPreferenceChangeListener { _, newValue ->
            viewModel.applyTheme(newValue.toString())
            true
        }
    }

    private fun setupTilePreference() {
        findPreference<Preference>(Constants.DIGITAL_ASSISTANT_TILE_PREF_KEY)?.setOnPreferenceClickListener {
            TileManager(requireContext()).requestAddTile()
            true
        }
    }

    private fun setupLibraryPreference() {
        findPreference<Preference>(Constants.LIBRARY_PREF_KEY)?.setOnPreferenceClickListener {
            val intent = Intent(requireContext(), LibraryActivity::class.java)
            startActivity(intent)
            true
        }
    }

    private fun setupLinkPreferences() {
        links.forEach { (key, url) ->
            findPreference<Preference>(key)?.setOnPreferenceClickListener {
                startActivity(Intent(Intent.ACTION_VIEW, url.toUri()))
                true
            }
        }
    }
}