package com.example.compose.main_activity


import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.compose.onboarding.NavGraph


class MainActivity : ComponentActivity() {

    //***NAVIGATION***
    lateinit var navController: NavHostController


    /*override fun onBackPressed() {
        super.onBackPressedDispatcher.onBackPressed()
        finish() // Close the activity
    }*/
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()
        setContent {


            //***NAVIGATION***
            navController = rememberNavController()
            NavGraph(navController = navController)

        }
    }
}








