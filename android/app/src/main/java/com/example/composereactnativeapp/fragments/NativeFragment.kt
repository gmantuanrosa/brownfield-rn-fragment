package com.example.composereactnativeapp.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import com.example.composereactnativeapp.activity.nativescreen.NativeScreen
import com.example.composereactnativeapp.react.FromNativeActivity

class NativeFragment : Fragment() {
    companion object {
        fun newInstance() = NativeFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return ComposeView(requireContext()).apply {
            setContent {
                NativeScreen(openReactNative = { openReactNative() })
            }
        }
    }

    private fun openReactNative() {
        val externalParams = Bundle().apply {
            putString("view_id", view?.id.toString())
        }

        val intent = Intent(requireContext(), FromNativeActivity::class.java).apply {
            putExtra("externalParams", externalParams)
        }

        startActivity(intent)
    }
}