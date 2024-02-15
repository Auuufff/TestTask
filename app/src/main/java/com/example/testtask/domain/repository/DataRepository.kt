package com.example.testtask.domain.repository

import com.example.testtask.data.model.VehicleDetailsEntity
import com.example.testtask.data.model.VehicleDetailsResponse
import com.example.testtask.data.model.VehicleIdResponse
import kotlinx.coroutines.flow.StateFlow

interface DataRepository {

    val cache: StateFlow<List<VehicleDetailsEntity>>
    suspend fun loadVehicles()
}