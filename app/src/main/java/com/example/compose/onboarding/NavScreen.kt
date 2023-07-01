package com.example.compose.onboarding

sealed class NavScreen(val route: String) {
    object OnBoarding : NavScreen("on_boarding")
    object HomeScreen : NavScreen("homepage_screen")
}