package com.example.compose.view

import android.annotation.SuppressLint
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.OnBackPressedDispatcher
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.example.compose.controller.postData
import com.example.compose.ui.theme.ComposeTheme

import retrofit2.*

class MainActivity2 : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.secondary,
                ) {
                    NewCanaryProjectTheme(onBackPressedDispatcher)
                }
            }
        }
    }



data class DataModel(
    var name: String,
    var job: String
)

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun NewCanaryProjectTheme (onBackPressedDispatcher: OnBackPressedDispatcher){
    Surface(
        modifier = Modifier.fillMaxSize(), color = MaterialTheme.colors.background
    ) {
        Scaffold(
            topBar = {

                TopAppBar(backgroundColor = Color(android.graphics.Color.parseColor("#D81B60")),
                    title = {
                        Text(
                            text = "Post Data",

                            modifier = Modifier.fillMaxWidth(),

                            color = Color.White
                        )
                    },
                    navigationIcon = {
                        IconButton(onClick = { onBackPressedDispatcher.onBackPressed() }) {
                            Icon(Icons.Default.ArrowBack, contentDescription = "Back", tint = Color.White)
                        }
                    },)
            }) {
            postData()
        }
    }
}







