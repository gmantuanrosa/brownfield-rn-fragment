package com.example.composereactnativeapp.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.composereactnativeapp.BuildConfig
import com.example.composereactnativeapp.R
import com.facebook.react.ReactFragment

class HybridFragment: Fragment() {
    private lateinit var reactNativeFragment: ReactFragment

    companion object {
        fun newInstance() = HybridFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.hybrid_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val defaultParams = Bundle().apply {
            putString("view_id", view.id.toString())
        }

        reactNativeFragment = ReactFragment.Builder()
            .setComponentName("FromNative")
            .setLaunchOptions(defaultParams)
            .setFabricEnabled(BuildConfig.IS_NEW_ARCHITECTURE_ENABLED)
            .build()

        childFragmentManager.beginTransaction()
            .add(R.id.fl_react_native_fragment, reactNativeFragment)
            .commit()
    }
}