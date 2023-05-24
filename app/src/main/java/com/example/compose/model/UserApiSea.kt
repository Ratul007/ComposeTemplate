package com.example.compose.model


import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path


interface UserApiSea {
    @Headers(
        "Accept: application/json"
    )
    @GET("users/{id}")
    fun getUserById(@Path("id") id: String): Call<UserModel?>?
}