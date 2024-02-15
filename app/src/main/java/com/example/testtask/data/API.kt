package com.example.testtask.data

import com.example.testtask.data.model.VehicleDetailsResponse
import com.example.testtask.data.model.VehicleIdResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface API {

    @GET("vehicles")
    suspend fun getVehicleList(): List<VehicleIdResponse>

    @GET("vehicles/{id}")
    suspend fun getVehicleDetails(@Path("id") id: String): VehicleDetailsResponse
}