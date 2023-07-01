package com.example.compose.postdata

import android.annotation.SuppressLint
import android.content.Context
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.OnBackPressedDispatcher
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.compose.ui.theme.customButtonColors
import com.example.compose.ui.theme.customTextFieldColors

import retrofit2.*
import retrofit2.converter.gson.GsonConverterFactory

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

fun postDataUsingRetrofit(
    ctx: Context,
    userName: MutableState<TextFieldValue>,
    job: MutableState<TextFieldValue>,
    result: MutableState<String>
) {
    val url = "https://reqres.in/api/"

    val retrofit = Retrofit.Builder()
        .baseUrl(url)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val retrofitAPI = retrofit.create(UserApi::class.java)
    val dataModel = PostDataModel(userName.value.text, job.value.text)
    val call: Call<PostDataModel?>? = retrofitAPI.postData(dataModel)
    call!!.enqueue(object : Callback<PostDataModel?> {
        override fun onResponse(call: Call<PostDataModel?>, response: Response<PostDataModel?>) {
            Toast.makeText(ctx, "Data posted to API", Toast.LENGTH_SHORT).show()
            val model: PostDataModel? = response.body()
            val resp = "User Name : " + model!!.name + "\n" + "Job : " + model.job
            result.value = resp
        }

        override fun onFailure(call: Call<PostDataModel?>, t: Throwable) {
            result.value = "Error found is : " + t.message
        }
    })

}




