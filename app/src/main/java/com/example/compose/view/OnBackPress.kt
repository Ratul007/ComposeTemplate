package com.example.compose.view

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import topAndNavigationBarBackButton

class MainActivity3 : ComponentActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

            topAndNavigationBarBackButton(onBackPressedDispatcher)
        }
    }
}









