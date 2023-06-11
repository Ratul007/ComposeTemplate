package com.example.compose.controller

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextField
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
import com.example.compose.view.DataModel
import com.example.compose.model.UserApi
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Composable
fun postData() {

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
            textStyle = TextStyle(color = androidx.compose.ui.graphics.Color.Black, fontSize = 15.sp),
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
            textStyle = TextStyle(color = androidx.compose.ui.graphics.Color.Black, fontSize = 15.sp),
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


private fun postDataUsingRetrofit(
    ctx: Context,
    userName: MutableState<TextFieldValue>,
    job: MutableState<TextFieldValue>,
    result: MutableState<String>
) {
    var url = "https://reqres.in/api/"

    val retrofit = Retrofit.Builder()
        .baseUrl(url)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val retrofitAPI = retrofit.create(UserApi::class.java)
    val dataModel = DataModel(userName.value.text, job.value.text)
    val call: Call<DataModel?>? = retrofitAPI.postData(dataModel)
    call!!.enqueue(object : Callback<DataModel?> {
        override fun onResponse(call: Call<DataModel?>?, response: Response<DataModel?>) {
            Toast.makeText(ctx, "Data posted to API", Toast.LENGTH_SHORT).show()
            val model: DataModel? = response.body()
            val resp =  "User Name : " + model!!.name + "\n" + "Job : " + model!!.job
            result.value = resp
        }

        override fun onFailure(call: Call<DataModel?>?, t: Throwable) {
            result.value = "Error found is : " + t.message
        }
    })

}