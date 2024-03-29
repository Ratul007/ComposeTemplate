package com.example.compose.home

import android.annotation.SuppressLint
import android.content.Intent
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.compose.animation.Animation
import com.example.compose.animation.BasicAnimation
import com.example.compose.bottom_navigation.BottomNavigation
import com.example.compose.onbackpress.OnBackPress
import com.example.compose.util.navigation_drawer.MenuItem
import com.example.compose.postdata.PostData
import com.example.compose.retrofitapi.RetrofitApi
import com.example.compose.util.AppBar
import com.example.compose.util.navigation_drawer.DrawerBody
import com.example.compose.util.navigation_drawer.DrawerHeader
import kotlinx.coroutines.launch

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Preview
@Composable
fun HomePage() {

    val scaffoldState = rememberScaffoldState()
    val scope = rememberCoroutineScope()

    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
            AppBar(title = "HomePage",
                onNavigationIconClick = {
                    scope.launch {
                        scaffoldState.drawerState.open()
                    }
                }
            )
        },
        content = {

            LayoutData()

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
fun LayoutData() {

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
                CardView(title = "A", subtitle = "Post Data", onClick = {
                    context.startActivity(Intent(context, PostData::class.java))
                })
                Spacer(modifier = Modifier.height(20.dp))
            }
            item {
                CardView(title = "B", subtitle = "OnBackPress", onClick = {
                    context.startActivity(Intent(context, OnBackPress::class.java))
                })
                Spacer(modifier = Modifier.height(20.dp))
            }
            item {
                CardView(title = "C", subtitle = "Retrofit Api", onClick = {
                    context.startActivity(Intent(context, RetrofitApi::class.java))
                })
                Spacer(modifier = Modifier.height(20.dp))
            }
            item {
                CardView(title = "D", subtitle = "Bottom Navigation", onClick = {
                    context.startActivity(Intent(context, BottomNavigation::class.java))
                })
                Spacer(modifier = Modifier.height(20.dp))
            }
            item {
                CardView(title = "E", subtitle = "Animation", onClick = {
                    context.startActivity(Intent(context, Animation::class.java))
                })
                Spacer(modifier = Modifier.height(20.dp))
            }
            item {
                CardView(title = "F", subtitle = "Basic Animation", onClick = {
                    context.startActivity(Intent(context, BasicAnimation::class.java))
                })
                Spacer(modifier = Modifier.height(20.dp))
            }
        }
    }
}


@Composable
fun CardView(title: String, subtitle: String, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .height(200.dp)
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