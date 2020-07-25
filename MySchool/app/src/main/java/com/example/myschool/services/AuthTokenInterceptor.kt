package com.example.myschool.services

import okhttp3.Interceptor
import okhttp3.Response

class AuthTokenInterceptor(val token: String) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val requBuilder = chain.request().newBuilder()
        requBuilder.addHeader("Authorization", "token $token")
        return chain.proceed(requBuilder.build())
    }
}