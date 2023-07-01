package com.example.compose.retrofitapi

import retrofit2.http.GET

interface DepartmentApiService {
    @GET("department")
    suspend fun getDepartments(): DepartmentListModel
}
