package com.example.testtask.common.network

import okhttp3.Interceptor
import okhttp3.Response

class AuthInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {

        val request = chain.request().newBuilder()
            .addHeader("x-api-key", "todo") // todo: replace with actual api key
            .build()

        return chain.proceed(request)
    }
}