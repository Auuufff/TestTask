package com.example.testtask.domain.repository

interface LocationRepository {
    fun getDistance(
        lat1: Double,
        lon1: Double,
        lat2: Double,
        lon2: Double
    ): Int
}