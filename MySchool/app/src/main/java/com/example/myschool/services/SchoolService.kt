package com.example.myschool.services

import com.example.myschool.model.UserAuthenticationToken
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface SchoolService {

    @FormUrlEncoded
    @POST("/api-authenticate/")
    fun authorizeUser(@Field("username") username: String, @Field("password") password: String): Call<UserAuthenticationToken>
}