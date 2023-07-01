package com.example.compose.retrofitapi

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class DepartmentInstance {

    val retrofit = Retrofit.Builder()
        .baseUrl("http://doctorhelpcare.com/api/Apihome/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val departmentApiService = retrofit.create(DepartmentApiService::class.java)


}