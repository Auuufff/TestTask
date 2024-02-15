package com.example.testtask.data.model

data class VehicleDetailsResponse(
    val vehicleId: String,
    val location: LocationResponse
)

data class LocationResponse(
    val latitude: Double,
    val longitude: Double,
)