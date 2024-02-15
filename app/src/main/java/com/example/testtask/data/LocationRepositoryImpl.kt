package com.example.testtask.data

import android.location.Location
import com.example.testtask.domain.repository.LocationRepository
import javax.inject.Inject
import kotlin.math.absoluteValue

class LocationRepositoryImpl @Inject constructor() : LocationRepository {
    override fun getDistance(lat1: Double, lon1: Double, lat2: Double, lon2: Double): Int {
        val result = FloatArray(3)
        Location.distanceBetween(lat1, lon1, lat2, lon2, result)
        return result.last().toInt().absoluteValue
    }
}