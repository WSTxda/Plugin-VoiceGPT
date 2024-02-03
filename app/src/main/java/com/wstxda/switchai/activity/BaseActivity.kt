package com.wstxda.switchai.activity

import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.view.View
import androidx.activity.SystemBarStyle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

abstract class BaseActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdgeNoContrast()
    }

    protected fun setupToolbar(toolbar: Toolbar, showBackButton: Boolean = true) {
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(showBackButton)
        if (showBackButton) {
            toolbar.setNavigationOnClickListener { onBackPressedDispatcher.onBackPressed() }
        }
    }

    private fun enableEdgeToEdgeNoContrast() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            enableEdgeToEdge(
                navigationBarStyle = SystemBarStyle.auto(Color.TRANSPARENT, Color.TRANSPARENT)
            )
            window.isNavigationBarContrastEnforced = false
        }
    }

    protected fun applySystemBarInsets(target: View) {
        ViewCompat.setOnApplyWindowInsetsListener(target) { view, insets ->
            val systemBarsInsets = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            view.setPadding(
                systemBarsInsets.left, 0, systemBarsInsets.right, systemBarsInsets.bottom
            )
            WindowInsetsCompat.CONSUMED
        }
    }
}