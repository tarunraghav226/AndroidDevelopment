package com.example.myschool.services

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ServiceBuilder {

    private const val BASE_URL = "https://campusface.pythonanywhere.com/"

    private val loggingInterceptor =
        HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)

    private val client = OkHttpClient.Builder().addInterceptor(loggingInterceptor)

    private var authenticatedInterceptor = AuthTokenInterceptor("NA")

    private val retrofitBuilder = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())

    fun <T> buildService(service: Class<T>): T {

        //if client contains authentication interceptor then remove it
        if (client.interceptors().contains(authenticatedInterceptor)) {
            client.interceptors().remove(authenticatedInterceptor)
        }

        retrofitBuilder.client(client.build())
        val retrofit = retrofitBuilder.build()
        return retrofit.create(service)
    }

    fun <T> buildAuthenticatedService(service: Class<T>, authToken: String): T {

        //if client doesn't contains authentication interceptor then include it
        if (!client.interceptors().contains(authenticatedInterceptor)) {
            authenticatedInterceptor = AuthTokenInterceptor(authToken)
            client.addInterceptor(authenticatedInterceptor)
        }

        retrofitBuilder.client(client.build())
        val retrofit = retrofitBuilder.build()
        return retrofit.create(service)
    }
}