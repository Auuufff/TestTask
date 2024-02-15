package com.example.testtask.presentation.app

import androidx.compose.runtime.Composable
import com.example.testtask.presentation.app.theme.TestTaskTheme
import com.example.testtask.presentation.home.HomeScreen

@Composable
fun AppScreen() {
    TestTaskTheme {
        HomeScreen()
    }
}