package com.example.testtask.presentation.app

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.testtask.domain.usecase.LoadVehicleListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val loadVehicleListUseCase: LoadVehicleListUseCase,
) : ViewModel() {

    private val coroutineExceptionHandler = CoroutineExceptionHandler { _, throwable -> handleException(throwable) }

    private var autoLoading = true

    init {
        viewModelScope.launch(coroutineExceptionHandler) {
            while (true) {
                if (autoLoading) {
                    loadVehicleListUseCase()
                }
                delay(5_000)
            }
        }
    }

    fun onPaused() {
        autoLoading = false
    }

    fun onResumed() {
        autoLoading = true
    }

    private fun handleException(throwable: Throwable) {
        Log.e("MainViewModel", "Error", throwable)
    }
}