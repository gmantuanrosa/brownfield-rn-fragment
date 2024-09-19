package com.example.composereactnativeapp.react

import android.os.Bundle
import com.facebook.react.ReactActivity
import com.facebook.react.ReactActivityDelegate
import com.facebook.react.ReactInstanceManager
import com.facebook.react.ReactRootView
import com.facebook.react.defaults.DefaultNewArchitectureEntryPoint.fabricEnabled
import com.facebook.react.defaults.DefaultReactActivityDelegate

abstract class BaseReactNativeActivity : ReactActivity() {

    private val mergedParams: Bundle = Bundle()
    private lateinit var reactInstanceManager: ReactInstanceManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        reactInstanceManager = reactNativeHost.reactInstanceManager
        if (reactInstanceManager.hasStartedCreatingInitialContext()) {
            setReactNativeContentView()
        }
    }

    /**
     * Define internal parameters to be passed to React Native.
     */
    private fun getInternalParams(): Bundle {
        return Bundle().apply {
            putBoolean("isFabricEnabled", fabricEnabled)
        }
    }

    /**
     * Returns the instance of the [ReactActivityDelegate]. We use [DefaultReactActivityDelegate]
     * which allows you to enable New Architecture with a single boolean flags [fabricEnabled]
     */
    override fun createReactActivityDelegate(): ReactActivityDelegate =
        object : DefaultReactActivityDelegate(this, mainComponentName ?: "", fabricEnabled) {
            override fun getLaunchOptions(): Bundle {
                val externalParams = intent?.extras?.getBundle("externalParams") ?: Bundle()
                val internalParams = getInternalParams()

                // Merge internal and external parameters
                mergedParams.apply {
                    putAll(internalParams)
                    putAll(externalParams)
                }

                return mergedParams
            }
        }

    override fun onWindowFocusChanged(hasFocus: Boolean) {
        try {
            if (reactInstanceManager.hasStartedCreatingInitialContext()) {
                super.onWindowFocusChanged(hasFocus)
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    private fun setReactNativeContentView() {
        val rootView = ReactRootView(this)
        rootView.startReactApplication(reactInstanceManager, mainComponentName, mergedParams)
        setContentView(rootView)
    }
}