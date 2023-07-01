package com.example.compose.retrofitapi

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
import androidx.compose.runtime.MutableState
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
import com.example.compose.ui.theme.customButtonColors
import com.example.compose.ui.theme.customTextFieldColors
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitApi : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            // A surface container using the 'background' color from the theme
            Surface(
                modifier = Modifier.fillMaxSize(),
                color = MaterialTheme.colors.background
            ) {
                RetroFitScreen(onBackPressedDispatcher)
            }
        }
    }
}


@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun RetroFitScreen(onBackPressedDispatcher: OnBackPressedDispatcher) {
    Scaffold(
        topBar = {
            TopAppBar(
                backgroundColor = Color(android.graphics.Color.parseColor("#D81B60")),
                title = {
                    Text(
                        text = "***Start 'npm json server start' ",
                        modifier = Modifier.fillMaxWidth(),
                        color = androidx.compose.ui.graphics.Color.White
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
                        style = MaterialTheme.typography.h3,
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
                        textStyle = TextStyle(
                            color = androidx.compose.ui.graphics.Color.Black,
                            fontSize = 15.sp
                        ),
                        singleLine = true,
                        colors = customTextFieldColors()
                    )

                    Spacer(modifier = Modifier.height(15.dp))

                    Box(modifier = Modifier.padding(40.dp, 0.dp, 40.dp, 0.dp)) {
                        Button(
                            colors = customButtonColors(),
                            onClick = {
                                /*val data = SendRequest(
                                    id = id.value.text,
                                    profileState = profile
                                )*/

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

fun SendRequest(
    id: String,
    profileState: MutableState<ProfileModel>
) {
    val retrofit = Retrofit.Builder()
        .baseUrl("http://192.168.183.106:3000/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val api = retrofit.create(UserApiSea::class.java)

    val call: Call<UserModel?>? = api.getUserById(id);

    call!!.enqueue(object : Callback<UserModel?> {
        override fun onResponse(call: Call<UserModel?>, response: Response<UserModel?>) {
            if (response.isSuccessful) {
                Log.d("Main", "success!" + response.body().toString())
                profileState.value = response.body()!!.profile
            }
        }

        override fun onFailure(call: Call<UserModel?>, t: Throwable) {
            Log.e("Main", "Failed mate " + t.message.toString())
        }
    })
}

