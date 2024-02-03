package com.wstxda.switchai.activity

import android.os.Bundle

abstract class AssistantActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        onCreateInternal()
        finish()
    }

    abstract fun onCreateInternal()
}