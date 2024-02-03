@file:Suppress("DEPRECATION")

package com.wstxda.switchai.activity

import android.os.Bundle
import com.mikepenz.aboutlibraries.LibsBuilder
import com.wstxda.switchai.R
import com.wstxda.switchai.databinding.ActivityLibraryBinding

class LibraryActivity : BaseActivity() {

    private val binding by lazy { ActivityLibraryBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        setupToolbar(binding.toolbar)
        binding.collapsingToolbar.title = getString(R.string.pref_used_library_summary)

        if (savedInstanceState == null) {
            val fragment = LibsBuilder().withEdgeToEdge(true).supportFragment()
            supportFragmentManager.beginTransaction()
                .replace(binding.libraryFragmentContainer.id, fragment).commit()
        }
    }
}