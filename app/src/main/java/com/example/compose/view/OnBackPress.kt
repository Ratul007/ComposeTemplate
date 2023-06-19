package com.example.compose.view

import TopAndNavigationBarBackButton
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent

class MainActivity3 : ComponentActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

            TopAndNavigationBarBackButton(onBackPressedDispatcher)
        }
    }
}









