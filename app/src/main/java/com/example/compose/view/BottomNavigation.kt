package com.example.compose.view


import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Surface
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.compose.rememberNavController
import com.example.compose.controller.BottomNavigationBar
import com.example.compose.controller.NavigationSetup

class BottomNavigation : ComponentActivity() {

    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
                Surface {
                    val navController = rememberNavController()
                    Scaffold(
                        topBar = {

                            TopAppBar(backgroundColor = Color(android.graphics.Color.parseColor("#D81B60")),
                                title = {
                                    Text(
                                        text = "BottomNavigation",

                                        modifier = Modifier.fillMaxWidth(),

                                        color = androidx.compose.ui.graphics.Color.White
                                    )
                                },
                                navigationIcon = {
                                    IconButton(onClick = { onBackPressedDispatcher.onBackPressed() }) {
                                        Icon(Icons.Default.ArrowBack, contentDescription = "Back", tint = androidx.compose.ui.graphics.Color.White)
                                    }
                                },)
                        },
                        bottomBar = { BottomNavigationBar(navController) }
                    ) {
                        NavigationSetup(navController = navController)
                    }
                }
            }
        }
    }

