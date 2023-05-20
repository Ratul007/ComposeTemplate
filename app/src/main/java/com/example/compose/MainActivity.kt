package com.example.compose

import android.annotation.SuppressLint
import android.content.Intent
import android.content.res.Resources.Theme
import androidx.compose.material.Text
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Settings
import androidx.compose.runtime.rememberCoroutineScope

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

            topAndNavigationBar()

        }
    }
}


@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Preview
@Composable
fun topAndNavigationBar() {

    val scaffoldState = rememberScaffoldState()
    val scope = rememberCoroutineScope()

    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
            AppBar(
                onNavigationIconClick = {
                    scope.launch {
                        scaffoldState.drawerState.open()
                    }
                }
            )
        },
        content = {

                  layoutData()

        },

        drawerGesturesEnabled = scaffoldState.drawerState.isOpen,
        drawerContent = {
            DrawerHeader()
            DrawerBody(
                items = listOf(
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
                ),
                onItemClick = {
                    println("Clicked on ${it.title}")
                }
            )
        }
    )
}


@Composable
fun layoutData(){

    val context = LocalContext.current

    Box(
        contentAlignment = Alignment.TopCenter,
        modifier = Modifier.fillMaxSize()
    ) {
        LazyColumn(
            modifier = Modifier.fillMaxWidth(),
            contentPadding = PaddingValues(vertical = 16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            item {
                CardView(title = "Tree", subtitle = "Apple", onClick = {
                    context.startActivity(Intent(context, MainActivity2::class.java))
                })
                Spacer(modifier = Modifier.height(20.dp))
            }
            item {
                CardView(title = "Animal", subtitle = "Ape", onClick = {
                    context.startActivity(Intent(context, MainActivity3::class.java))
                })
                Spacer(modifier = Modifier.height(20.dp))
            }
            item {
                CardView(title = "Sea", subtitle = "Pacific Ocean", onClick = {
                    context.startActivity(Intent(context, MainActivity4::class.java))
                })
                Spacer(modifier = Modifier.height(20.dp))
            }
            item {
                CardView(title = "River", subtitle = "Nil", onClick = {

                })
                Spacer(modifier = Modifier.height(20.dp))
            }
            item {
                CardView(title = "Bike", subtitle = "Bajaj", onClick = {

                })
                Spacer(modifier = Modifier.height(20.dp))
            }
            item {
                CardView(title = "Watch", subtitle = "Casio", onClick = {

                })
                Spacer(modifier = Modifier.height(20.dp))
            }
        }
    }
}


@Composable
fun CardView(title:String, subtitle:String, onClick: () -> Unit){
    Card(
        modifier = Modifier
            .height(300.dp)
            .width(300.dp)
            .clickable { onClick.invoke() },
        backgroundColor = Color.LightGray,
        shape = RoundedCornerShape(8.dp)
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
                .background(Color(android.graphics.Color.parseColor("#EDEDED")))
                .border(2.dp, Color.Gray),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center


        ) {
            Text(
                text = title,
                style = MaterialTheme.typography.h6,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(8.dp),
            )
            Text(
                text = subtitle,
                style = MaterialTheme.typography.body1,
            )
        }
    }
}




