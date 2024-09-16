package com.example.composereactnativeapp.activity

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.isImeVisible
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.fragment.app.FragmentManager
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.composereactnativeapp.activity.nav.MainBottomMenuItems
import com.example.composereactnativeapp.activity.nav.MainNavHost

@OptIn(ExperimentalLayoutApi::class, ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(
    modifier: Modifier = Modifier,
    fragmentManager: FragmentManager
) {
    val navController = rememberNavController()
    val backStack by navController.currentBackStackEntryAsState()

    Scaffold(
        modifier = modifier,
        topBar = {
            val currentMenu by remember {
                derivedStateOf { MainBottomMenuItems.mapRouteToMenuItem(backStack?.destination?.route) }
            }

            Surface(shadowElevation = 6.dp) {
                Column {
                    CenterAlignedTopAppBar(
                        colors = TopAppBarDefaults.centerAlignedTopAppBarColors()
                            .copy(containerColor = Color.White),
                        title = {
                            Text(
                                text = currentMenu.title,
                                style = MaterialTheme.typography.titleSmall,
                            )
                        }
                    )
                }
            }
        },
        bottomBar = {
            NavigationBar(
                containerColor = Color.White,
                tonalElevation = 4.dp,
            ) {
                val navBackStackEntry by navController.currentBackStackEntryAsState()
                val currentRoute = navBackStackEntry?.destination?.route
                MainBottomMenuItems.entries.forEach { screen ->
                    NavigationBarItem(
                        label = { Text(screen.title) },
                        selected = currentRoute == screen.route,
                        icon = {
                            Icon(
                                imageVector = screen.icon,
                                contentDescription = null
                            )
                        },
                        onClick = {
                            navController.navigate(screen.route) {
                                popUpTo(navController.graph.startDestinationId) {
                                    saveState = true
                                }
                                launchSingleTop = true
                                restoreState = true
                            }
                        }
                    )
                }
            }
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .imePadding()
                .padding(
                    top = innerPadding.calculateTopPadding(),
                    bottom = if (WindowInsets.isImeVisible) 0.dp else innerPadding.calculateBottomPadding()
                )
        ) {
            MainNavHost(
                navController = navController,
                fragmentManager = fragmentManager,
                modifier = Modifier.padding(innerPadding)
            )
        }
    }
}