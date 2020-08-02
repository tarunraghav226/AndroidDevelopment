package com.example.myschool.services

import com.example.myschool.model.Student
import com.example.myschool.model.StudentSearchResult
import com.example.myschool.model.TeacherSearchResult
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST

interface AuthenticatedService {
    @GET("/api/dashboard/")
    fun getUserDetails(): Call<Student>

    @POST("/api/search/")
    @FormUrlEncoded
    fun searchStudent(@Field("search_whom") search_whom: String, @Field("id") id: String): Call<StudentSearchResult>

    @POST("/api/search/")
    @FormUrlEncoded
    fun searchTeacher(@Field("search_whom") search_whom: String, @Field("id") id: String): Call<TeacherSearchResult>
}