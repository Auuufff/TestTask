package com.example.testtask.presentation.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.testtask.domain.model.VehicleDetails
import com.example.testtask.presentation.details.DetailsScreen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
) {
    val navController = rememberNavController()

    val viewModel = hiltViewModel<HomeViewModel>()
    val state by viewModel.state.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Close: ${state.closeToPoint}") },
            )
        }
    )
    { paddingValues ->
        NavHost(
            modifier = Modifier.padding(paddingValues),
            navController = navController,
            startDestination = "home",
        ) {
            composable("home") {
                Content(
                    state = state,
                    onDetailsTap = { id -> navController.navigate("details/$id") }
                )
            }
            composable("details/{id}") { backStackEntry ->
                DetailsScreen(
                    id = backStackEntry.arguments?.getString("id")
                ) { navController.popBackStack() }
            }
        }
    }
}

@Composable
private fun Content(state: HomeState, onDetailsTap: (String) -> Unit) {
    if (state.vehicleList.isEmpty()) {
        Text(text = "No data")
        return
    }

    LazyColumn(modifier = Modifier.fillMaxSize()) {
        items(state.vehicleList) { vehicle ->
            Item(
                item = vehicle,
                onDetailsTap = onDetailsTap,
            )
        }
    }
}

@Composable
private fun Item(
    item: VehicleDetails,
    onDetailsTap: (String) -> Unit
) {

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onDetailsTap(item.id) }
            .padding(12.dp)
    ) {
        Text(
            modifier = Modifier.padding(horizontal = 12.dp, vertical = 8.dp),
            text = "ID: ${item.id}"
        )
        Text(
            modifier = Modifier.padding(horizontal = 12.dp, vertical = 8.dp),
            text = "Status: ${item.status}",
        )
    }
}