package com.wstxda.switchai.logic

import android.util.Log
import kotlin.getOrElse
import kotlin.runCatching

object RootChecker {
    private const val TAG = "RootChecker"

    fun isRootAvailable(): Boolean = runCatching {
        val process = ProcessBuilder("su", "-c", "id").start()
        process.waitFor() == 0
    }.getOrElse {
        Log.e(TAG, "Root check failed", it)
        false
    }

    fun launchRootActivity(packageName: String, activityName: String): Boolean = runCatching {
        val process =
            ProcessBuilder("su", "-c", "am", "start", "-n", "$packageName/$activityName").start()
        process.waitFor() == 0
    }.getOrElse {
        Log.e(TAG, "Failed to launch root activity", it)
        false
    }
}