package com.example.testtask.domain.usecase

import com.example.testtask.domain.repository.DataRepository
import javax.inject.Inject

class LoadVehicleListUseCase @Inject constructor(
    private val dataRepository: DataRepository,
) {
    suspend operator fun invoke() {
        return dataRepository.loadVehicles()
    }
}