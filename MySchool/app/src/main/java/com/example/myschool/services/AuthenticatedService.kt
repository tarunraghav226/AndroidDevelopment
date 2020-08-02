package com.example.myschool.services

import com.example.myschool.model.Student
import retrofit2.Call
import retrofit2.http.GET

interface AuthenticatedService {
    @GET("/api/dashboard/")
    fun getUserDetails(): Call<Student>
}