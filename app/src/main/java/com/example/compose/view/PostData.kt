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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.compose.controller.postDataUsingRetrofit
import com.example.compose.ui.theme.customButtonColors
import com.example.compose.ui.theme.customTextFieldColors

import retrofit2.*

class PostData : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.secondary,
                ) {
                    PostDataTheme(onBackPressedDispatcher)
                }
            }
        }
    }


@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun PostDataTheme (onBackPressedDispatcher: OnBackPressedDispatcher){
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
            Postdata()
        }
    }
}


@Composable
fun Postdata() {

    val ctx = LocalContext.current

    val userName = remember {
        mutableStateOf(TextFieldValue())
    }
    val job = remember {
        mutableStateOf(TextFieldValue())
    }
    val response = remember {
        mutableStateOf("")
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .fillMaxHeight()
            .fillMaxWidth(),

        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Post Data",
            color = Color(android.graphics.Color.parseColor("#D81B60")),
            style = MaterialTheme.typography.h3,
            fontFamily = FontFamily.Default,
            fontWeight = FontWeight.Bold, textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.height(5.dp))
        TextField(
            value = userName.value,
            onValueChange = { userName.value = it },
            placeholder = { Text(text = "Enter your name") },
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            textStyle = TextStyle(color = Color.Black, fontSize = 15.sp),
            singleLine = true,
            colors = customTextFieldColors()
        )
        Spacer(modifier = Modifier.height(5.dp))
        TextField(
            value = job.value,
            onValueChange = { job.value = it },
            placeholder = {
                Text(
                    text = "Enter your job")
            },
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            textStyle = TextStyle(color = Color.Black, fontSize = 15.sp),
            singleLine = true,
            colors = customTextFieldColors()
        )

        Spacer(modifier = Modifier.height(10.dp))
        Button(
            colors = customButtonColors(),
            onClick = {
                postDataUsingRetrofit(
                    ctx, userName, job, response
                )
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Text(text = "Post Data", modifier = Modifier.padding(8.dp))
        }
        Spacer(modifier = Modifier.height(20.dp))
        Text(
            text = response.value,
            color = androidx.compose.ui.graphics.Color.Black,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold, modifier = Modifier
                .padding(10.dp)
                .fillMaxWidth(),
            textAlign = TextAlign.Center
        )
    }
}








