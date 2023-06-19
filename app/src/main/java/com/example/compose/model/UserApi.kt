package com.example.compose.model



import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.http.*


interface UserApi {
    // as we are making a post request to post a data
    // so we are annotating it with post
    // and along with that we are passing a parameter as users
    @POST("users")
    fun  // on below line we are creating a method to post our data.
            postData(@Body dataModel: PostDataModel?): Call<PostDataModel?>?
}