package com.example.compose.view

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import android.view.animation.OvershootInterpolator
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import kotlinx.coroutines.launch
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Settings
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.compose.R
import com.example.compose.controller.DrawerBody
import com.example.compose.controller.DrawerHeader
import com.example.compose.model.MenuItem
import kotlinx.coroutines.delay
import layoutData
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.viewinterop.AndroidView

class MainActivity : ComponentActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        setContent {

           // window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN

            Navigation()

        }
    }

}

@Composable
fun Navigation() {
    val navController = rememberNavController()
    NavHost(
        navController = navController, startDestination = "splash_screen"
    ) {
        composable("splash_screen") {

            SplashScreen(navController = navController)

           /* AndroidView(
                factory = { context ->
                    View(context).apply {
                        // Set the system UI visibility flags
                        systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN
                    }
                },
            )*/
        }

        // Main Screen
        composable("main_screen") {


            topAndNavigationBar()
        }
    }
}

@Composable
fun SplashScreen(navController: NavController) {
    val scale = remember {
        Animatable(0f)
    }

    // Animation
    LaunchedEffect(key1 = true) {
        scale.animateTo(
            targetValue = 0.7f,
            // tween Animation
            animationSpec = tween(durationMillis = 800, easing = {
                OvershootInterpolator(4f).getInterpolation(it)
            })
        )
        // Customize the delay time
        delay(3000L)
        navController.navigate("main_screen")
    }

    // Image
    Box(
        contentAlignment = Alignment.Center, modifier = Modifier.fillMaxSize()
    ) {
        // Change the logo
        Image(
            painter = painterResource(id = R.drawable.icon),
            contentDescription = "Logo",
            modifier = Modifier.scale(scale.value)
        )
    }
}

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Preview
@Composable
fun topAndNavigationBar() {

    val scaffoldState = rememberScaffoldState()
    val scope = rememberCoroutineScope()

    Scaffold(scaffoldState = scaffoldState, topBar = {
        com.example.compose.controller.AppBar(title = "HomePage", onNavigationIconClick = {
            scope.launch {
                scaffoldState.drawerState.open()
            }
        })
    }, content = {

        layoutData()

    },

        drawerGesturesEnabled = scaffoldState.drawerState.isOpen, drawerContent = {
            DrawerHeader()
            DrawerBody(items = listOf(
                MenuItem(
                    id = "home",
                    title = "Home",
                    contentDescription = "Go to home screen",
                    icon = Icons.Default.Home
                ),
                MenuItem(
                    id = "settings",
                    title = "Settings",
                    contentDescription = "Go to settings screen",
                    icon = Icons.Default.Settings
                ),
                MenuItem(
                    id = "help",
                    title = "Help",
                    contentDescription = "Get help",
                    icon = Icons.Default.Info
                ),
            ), onItemClick = {
                println("Clicked on ${it.title}")
            })
        })
}















