package com.example.compose

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.navigation.NavController
import androidx.activity.ComponentActivity
import androidx.activity.OnBackPressedDispatcher
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Scaffold
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.rememberNavController
import com.example.compose.ui.theme.ComposeTheme

class MainActivity3 : ComponentActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

            topAndNavigationBarBackButton(onBackPressedDispatcher)
        }
    }
}


@Composable
fun Greeting2(name: String) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        androidx.compose.material.Text(
            text = "$name!",
            style = androidx.compose.material.MaterialTheme.typography.h3,
        )
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview2() {
    ComposeTheme {
        Greeting2("Android")
    }
}


@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun topAndNavigationBarBackButton(onBackPressedDispatcher: OnBackPressedDispatcher) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Compose", color = Color.White, fontSize = 20.sp, fontWeight = FontWeight.Bold) },
                navigationIcon = {
                    IconButton(onClick = { onBackPressedDispatcher.onBackPressed() }) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Back", tint = Color.White)
                    }
                },
                backgroundColor = Color(android.graphics.Color.parseColor("#D81B60"))
            )
        }
    ) {
        Greeting2(name = "Animal")
    }
}

