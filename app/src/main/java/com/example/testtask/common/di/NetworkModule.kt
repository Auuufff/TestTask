package com.example.testtask.common.di

import com.example.testtask.common.network.AuthInterceptor
import com.example.testtask.data.API
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    @Provides
    fun provideRetrofit(): Retrofit {
        val okHttpClient = OkHttpClient.Builder()
            .addInterceptor(AuthInterceptor())
            .build()

        return Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl("https://5w53f1x8oa.execute-api.eu-west-1.amazonaws.com/dev/")
            .build()
    }

    @Provides
    fun provideAPI(retrofit: Retrofit): API {
        return retrofit.create(API::class.java)
    }
}