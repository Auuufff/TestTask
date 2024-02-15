package com.example.testtask.domain.model

data class VehicleDetails(
    val id: String,
    val distance: Int,
    val longitude: Double,
    val latitude: Double,
    val isClose: Boolean,
    val status: String,
)