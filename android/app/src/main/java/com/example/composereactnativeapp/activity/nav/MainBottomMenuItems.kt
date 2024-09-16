package com.example.composereactnativeapp.activity.nav

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Face
import androidx.compose.ui.graphics.vector.ImageVector

enum class MainBottomMenuItems(
    val route: String,
    val title: String,
    val icon: ImageVector
) {
    NATIVE(route = "native", title = "Native", icon = Icons.Outlined.Face),
    HYBRID(route = "hybrid", title = "React Native", icon = Icons.Outlined.Face);

    companion object {
        fun mapRouteToMenuItem(route: String?): MainBottomMenuItems = when (route) {
            NATIVE.route -> NATIVE
            HYBRID.route -> HYBRID
            else -> NATIVE
        }
    }
}