package com.example.testtask.presentation.details

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun DetailsScreen(id: String?, onBack: () -> Unit) {
    val viewModel = hiltViewModel<DetailsViewModel>()

    LaunchedEffect(Unit) {
        viewModel.init(id)
    }

    val state by viewModel.state.collectAsState()

    if (state.vehicle == null) {
        Text(text = "Ups, something went wrong")
        return
    }

    Column(modifier = Modifier.padding(24.dp)) {
        Text(text = "Details:")
        Spacer(modifier = Modifier.height(24.dp))
        Text(text = "ID: ${state.vehicle?.id}")
        Text(text = "Status: ${state.vehicle?.status}")
        Text(text = "Longitude: ${state.vehicle?.longitude}")
        Text(text = "Latitude: ${state.vehicle?.latitude}")
        Text(text = "Distance to Lausanne: ${state.vehicle?.distance}")

        Spacer(modifier = Modifier.height(24.dp))
        Button(onClick = onBack) {
            Text(text = "Back")
        }
    }

}