package com.example.compose.onboarding

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.compose.main_activity.HomePage
import com.google.accompanist.pager.ExperimentalPagerApi

@OptIn(ExperimentalPagerApi::class)
@Composable
fun NavGraph(navController: NavHostController) {


    NavHost(navController = navController, startDestination = NavScreen.OnBoarding.route) {
        composable(NavScreen.OnBoarding.route) {
            OnBoarding(navController)
        }
        composable(NavScreen.HomeScreen.route) {
            HomePage()
        }

    }
}



