package com.example.compose.controller

import android.annotation.SuppressLint
import android.content.Intent
import androidx.activity.OnBackPressedDispatcher
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import com.example.compose.view.FavoriteActivity
import com.example.compose.view.ListActivity
import com.example.compose.view.PersonActivity

@Composable
fun navigationBar(){

    var selectedItem by remember { mutableStateOf(0) }
    val items = listOf("Songs", "Artists", "Playlists")
    val context = LocalContext.current

    NavigationBar {
        items.forEachIndexed { index, item ->
            val icon = when (index) {
                0 -> Icons.Filled.Favorite
                1 -> Icons.Filled.Person
                2 -> Icons.Filled.List
                else -> Icons.Default.Warning // Provide a fallback icon if needed
            }
            NavigationBarItem(
                icon = { Icon(icon, contentDescription = item, tint = Color(android.graphics.Color.parseColor("#D81B60"))) },
                label = { Text(item, color = Color(android.graphics.Color.parseColor("#D81B60"))) },
                selected = selectedItem == index,
                onClick = {
                    selectedItem = index
                    when (index) {
                        0 -> context.startActivity(Intent(context, FavoriteActivity::class.java))
                        1 -> context.startActivity(Intent(context, PersonActivity::class.java))
                        2 -> context.startActivity(Intent(context, ListActivity::class.java))
                        else -> {
                            // Handle fallback case if needed
                        }
                    }
                }
            )
        }
    }
}

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun topAndNavigationBarBackButtonNil(onBackPressedDispatcher: OnBackPressedDispatcher) {
    androidx.compose.material.Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        "Bottom Navigation",
                        color = androidx.compose.ui.graphics.Color.White,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold
                    )
                },
                navigationIcon = {
                    IconButton(onClick = { onBackPressedDispatcher.onBackPressed() }) {
                        Icon(
                            Icons.Default.ArrowBack,
                            contentDescription = "Back",
                            tint = androidx.compose.ui.graphics.Color.White
                        )
                    }
                },
                backgroundColor = Color(android.graphics.Color.parseColor("#D81B60"))
            )
        }
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Bottom Navigation",
                color = Color(android.graphics.Color.parseColor("#D81B60")),
                style = MaterialTheme.typography.h3,
                fontFamily = FontFamily.Default,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center
            )
        }
    }
}
