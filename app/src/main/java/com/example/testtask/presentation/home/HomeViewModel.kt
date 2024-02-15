package com.example.testtask.presentation.home

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.testtask.domain.model.VehicleDetails
import com.example.testtask.domain.usecase.ObserveVehicleListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val observeVehicleListUseCase: ObserveVehicleListUseCase,
) : ViewModel() {

    private val coroutineExceptionHandler = CoroutineExceptionHandler { _, throwable -> handleException(throwable) }

    private val _state = MutableStateFlow(HomeState())
    val state: StateFlow<HomeState> get() = _state.asStateFlow()

    init {
        observeVehicleList()
    }

    private fun observeVehicleList() = viewModelScope.launch(coroutineExceptionHandler) {
        observeVehicleListUseCase().collect { items ->
            _state.update { st ->
                st.copy(
                    vehicleList = items,
                    closeToPoint = items.count { it.isClose }
                )
            }
        }
    }

    private fun handleException(throwable: Throwable) {
        Log.e("HomeViewModel", "Error", throwable)
    }
}

data class HomeState(
    val vehicleList: List<VehicleDetails> = emptyList(),
    val closeToPoint: Int = 0,
)
