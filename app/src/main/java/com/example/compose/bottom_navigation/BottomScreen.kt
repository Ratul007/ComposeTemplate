package com.example.compose.bottom_navigation

sealed class BottomScreen(val route: String) {
    object Home : BottomScreen("home_screen")
    object Settings : BottomScreen("settings_screen")
}