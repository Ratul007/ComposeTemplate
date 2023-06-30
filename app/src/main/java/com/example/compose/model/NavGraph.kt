package com.example.compose.model

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.compose.bottom_navigation.HomeScreen
import com.example.compose.onboarding.OnBoarding
import com.example.compose.view.HomePage
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



