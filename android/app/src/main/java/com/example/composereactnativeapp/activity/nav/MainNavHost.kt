package com.example.composereactnativeapp.activity.nav

import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.AndroidView
import androidx.fragment.app.FragmentContainerView
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.fragment.app.commit
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.composereactnativeapp.fragments.HybridFragment
import com.example.composereactnativeapp.fragments.NativeFragment

@Composable
fun MainNavHost(
    navController: NavHostController,
    fragmentManager: FragmentManager,
    modifier: Modifier = Modifier
) {
    NavHost(
        navController = navController,
        startDestination = MainBottomMenuItems.NATIVE.route,
        modifier = modifier
    ) {
        composable(route = MainBottomMenuItems.NATIVE.route) {
            FragmentContainer(
                modifier = Modifier.fillMaxSize(),
                fragmentManager = fragmentManager,
                commit = { add(it, NativeFragment.newInstance()) }
            )
        }

        composable(route = MainBottomMenuItems.HYBRID.route) {
            FragmentContainer(
                modifier = Modifier.fillMaxSize(),
                fragmentManager = fragmentManager,
                commit = { add(it, HybridFragment.newInstance()) }
            )
        }
    }
}

@Composable
fun FragmentContainer(
    modifier: Modifier = Modifier,
    fragmentManager: FragmentManager,
    commit: FragmentTransaction.(containerId: Int) -> Unit
) {
    val containerId by remember { mutableIntStateOf(View.generateViewId()) }

    AndroidView(
        modifier = modifier,
        factory = { context ->
            FragmentContainerView(context).apply {
                id = containerId
                layoutParams = ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.MATCH_PARENT
                )
            }.also { containerView ->
                fragmentManager.commit {
                    commit(containerView.id)
                }
            }
        }
    )
}
