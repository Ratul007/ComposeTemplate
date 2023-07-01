package com.example.compose.bottom_navigation

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Settings
import androidx.compose.ui.graphics.vector.ImageVector
import com.example.compose.R

sealed class BottomNavItem(
    val route: String,
    @StringRes val titleResId: Int,
    val icon: ImageVector
) {
    object Home : BottomNavItem(
        route = BottomScreen.Home.route,
        titleResId = R.string.screen_title_home,
        icon = Icons.Default.Home
    )

    object Settings : BottomNavItem(
        route = BottomScreen.Settings.route,
        titleResId = R.string.screen_title_settings,
        icon = Icons.Default.Settings
    )
}