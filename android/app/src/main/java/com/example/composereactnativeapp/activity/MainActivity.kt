package com.example.composereactnativeapp.activity

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.compose.ui.Modifier
import com.example.composereactnativeapp.ui.theme.ComposeReactNativeAppTheme
import com.facebook.react.modules.core.DefaultHardwareBackBtnHandler

class MainActivity : AppCompatActivity(), DefaultHardwareBackBtnHandler {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)

        setContent {
            ComposeReactNativeAppTheme {
                MainScreen(
                    modifier = Modifier,
                    fragmentManager = supportFragmentManager
                )
            }
        }
    }

    override fun invokeDefaultOnBackPressed() {
        super.onBackPressed()
    }
}