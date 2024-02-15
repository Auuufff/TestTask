package com.example.testtask.domain.usecase

import com.example.testtask.common.Constants
import com.example.testtask.domain.model.VehicleDetails
import com.example.testtask.domain.repository.DataRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class ObserveVehicleListUseCase @Inject constructor(
    private val dataRepository: DataRepository,
) {
    operator fun invoke(): Flow<List<VehicleDetails>> {
        return dataRepository.cache.map { list ->
            list.map { details ->
                VehicleDetails(
                    id = details.id,
                    distance = details.distance,
                    isClose = details.distance < Constants.CLOSE_DISTANCE,
                    latitude = details.latitude,
                    longitude = details.longitude,
                    status = if (details.distance <= Constants.CLOSE_DISTANCE) "Close" else "Far away"
                )
            }
        }
    }
}