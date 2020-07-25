package com.example.myschool.services

import com.example.myschool.model.StudentUser
import retrofit2.Call
import retrofit2.http.GET

interface AuthenticatedService {
    @GET("/api/dashboard/")
    fun getUserDetails(): Call<StudentUser>
}