package com.example.compose.controller

import android.content.Context
import android.widget.Toast
import androidx.compose.runtime.MutableState
import androidx.compose.ui.text.input.TextFieldValue
import com.example.compose.model.PostDataModel
import com.example.compose.model.UserApi
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory



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