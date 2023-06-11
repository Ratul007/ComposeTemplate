package com.example.compose.view

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.OnBackPressedDispatcher
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.compose.controller.customButtonColors
import com.example.compose.controller.customTextFieldColors
import com.example.compose.controller.sendRequest
import com.example.compose.model.ProfileModel
import com.example.compose.ui.theme.ComposeTheme

class MainActivity4 : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            // A surface container using the 'background' color from the theme
            Surface(
                modifier = Modifier.fillMaxSize(),
                color = MaterialTheme.colors.background
            ) {
                MainScreen(onBackPressedDispatcher)
            }
        }
    }
}

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun MainScreen(onBackPressedDispatcher: OnBackPressedDispatcher) {
    Scaffold(
        topBar = {
            TopAppBar(
                backgroundColor = Color(android.graphics.Color.parseColor("#D81B60")),
                title = {
                    Text(
                        text = "***Start 'npm json server start' ",
                        modifier = Modifier.fillMaxWidth(),
                        color = Color.White
                    )
                },
                navigationIcon = {
                    IconButton(onClick = { onBackPressedDispatcher.onBackPressed() }) {
                        Icon(
                            Icons.Default.ArrowBack,
                            contentDescription = "Back",
                            tint = Color.White
                        )
                    }
                }
            )
        },
        content = {

            Column(
                modifier = Modifier.fillMaxSize(),
            ) {
                Column(
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxWidth()
                        .padding(16.dp),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally

                ) {
                    val id = remember {
                        mutableStateOf(TextFieldValue())
                    }

                    val profile = remember {
                        mutableStateOf(
                            ProfileModel(
                                age = "",
                                name = "",
                                email = ""
                            )
                        )
                    }

                    Text(
                        text = "Retrofit Api",
                        color = Color(android.graphics.Color.parseColor("#D81B60")),
                        style = androidx.compose.material.MaterialTheme.typography.h3,
                        fontFamily = FontFamily.Default,
                        fontWeight = FontWeight.Bold, textAlign = TextAlign.Center
                    )

                    Spacer(modifier = Modifier.height(15.dp))


                    TextField(
                        value = id.value,
                        onValueChange = { id.value = it },
                        placeholder = { Text(text = "User ID") },
                        modifier = Modifier
                            .padding(16.dp)
                            .fillMaxWidth(),
                        textStyle = TextStyle(color = Color.Black, fontSize = 15.sp),
                        singleLine = true,
                        colors = customTextFieldColors()
                    )

                    Spacer(modifier = Modifier.height(15.dp))

                    Box(modifier = Modifier.padding(40.dp, 0.dp, 40.dp, 0.dp)) {
                        Button(
                            colors = customButtonColors(),
                            onClick = {
                                val data = sendRequest(
                                    id = id.value.text,
                                    profileState = profile
                                )

                                Log.d("Main Activity", profile.toString())
                            },
                        ) {
                            Text(text = "Get Data", modifier = Modifier.padding(8.dp))
                        }
                    }

                    Spacer(modifier = Modifier.height(15.dp))

                    // Text(text = profile.component1().toString(), fontSize = 40.sp)
                }

                Column(
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxWidth()
                        .padding(16.dp)
                ) {

                    val profile = remember {
                        mutableStateOf(
                            ProfileModel(
                                age = "",
                                name = "",
                                email = ""
                            )
                        )
                    }

                    Text(
                        text = "Age: ${profile.value.age}",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color(android.graphics.Color.parseColor("#D81B60"))
                    )
                    Text(
                        text = "Name: ${profile.value.name}",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color(android.graphics.Color.parseColor("#D81B60"))
                    )
                    Text(
                        text = "Email: ${profile.value.email}",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color(android.graphics.Color.parseColor("#D81B60"))
                    )
                }
            }
        }
    )
}


