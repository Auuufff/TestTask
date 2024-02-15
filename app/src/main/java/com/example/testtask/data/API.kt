package com.example.testtask.data

import retrofit2.http.GET

interface API {

    @GET("vehicles")
    suspend fun getVehicleList(): List<Any>// todo: replace with actual response type

    @GET("vehicles/{id}")
    suspend fun getVehicleDetails(id: String): Any// todo: replace with actual response type
}