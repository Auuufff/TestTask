package com.example.testtask.data

import com.example.testtask.data.model.VehicleDetailsEntity
import com.example.testtask.domain.repository.DataRepository
import com.example.testtask.domain.repository.LocationRepository
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject
import javax.inject.Singleton

private const val LAUSANNE_LATITUDE = 46.5223916
private const val LAUSANNE_LONGITUDE = 6.6314437

class DataRepositoryImpl @Inject constructor(
    private val api: API,
    private val locationRepository: LocationRepository,
) : DataRepository {

    private val _cache = MutableStateFlow(emptyList<VehicleDetailsEntity>())
    override val cache get() = _cache.asStateFlow()

    override suspend fun loadVehicles() {
        coroutineScope {
            val items = api.getVehicleList().map { item ->
                async { getVehicleDetails(item.vehicleId) }
            }.awaitAll()

            _cache.value = items
        }
    }

    private suspend fun getVehicleDetails(vehicleId: String): VehicleDetailsEntity {
        val details = api.getVehicleDetails(vehicleId)
        val distance = locationRepository.getDistance(
            details.location.latitude,
            details.location.longitude,
            LAUSANNE_LATITUDE,
            LAUSANNE_LONGITUDE,
        )
        return VehicleDetailsEntity(
            id = details.vehicleId,
            distance = distance,
            latitude = details.location.latitude,
            longitude = details.location.longitude,
        )
    }
}