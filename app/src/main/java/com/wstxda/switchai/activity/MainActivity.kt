package com.wstxda.switchai.activity

import android.os.Bundle
import com.wstxda.switchai.R
import com.wstxda.switchai.databinding.ActivityMainBinding

class MainActivity : BaseActivity() {

    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        applySystemBarInsets(binding.navHostContainer)

        setupToolbar(binding.toolbar, showBackButton = false)
        binding.collapsingToolbar.title = getString(R.string.app_settings)
    }
}