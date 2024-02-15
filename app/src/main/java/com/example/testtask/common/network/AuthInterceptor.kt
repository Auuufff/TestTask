package com.example.testtask.common.network

import okhttp3.Interceptor
import okhttp3.Response

class AuthInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {

        val request = chain.request().newBuilder()
            .addHeader("x-api-key", "5cvONtvu6n81IUQIu0ver8vtAON7reK23ZgCSN9E")
            .build()

        return chain.proceed(request)
    }
}