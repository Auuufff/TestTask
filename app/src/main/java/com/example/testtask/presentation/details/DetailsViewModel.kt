package com.example.testtask.presentation.details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.testtask.domain.model.VehicleDetails
import com.example.testtask.domain.usecase.ObserveVehicleListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.mapNotNull
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(
    private val observeVehicleListUseCase: ObserveVehicleListUseCase,
) : ViewModel() {

    private val _state = MutableStateFlow(DetailsState())
    val state get() = _state.asStateFlow()

    fun init(id: String?) {
        viewModelScope.launch {
            observeVehicleListUseCase()
                .mapNotNull { list ->
                    list.find { it.id == id }
                }
                .collect { vehicle ->
                    _state.value = DetailsState(vehicle)
                }
        }
    }
}

class DetailsState(
    val vehicle: VehicleDetails? = null,
)