package com.flickrimagesearch.ui.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.flickrimagesearch.ui.components.ImageGrid
import com.flickrimagesearch.ui.components.SearchBar
import com.flickrimagesearch.ui.viewmodel.SearchStates
import com.flickrimagesearch.ui.viewmodel.FlickrViewModel

@Composable
fun FlickrSearchScreen(viewModel: FlickrViewModel) {
    val uiState by viewModel.uiState.collectAsState()
    val searchQuery by viewModel.searchQuery.collectAsState()

    Column {
        SearchBar(
            query = searchQuery,
            onQueryChange = { viewModel.updateSearchQuery(it) }
        )

        when (val state = uiState) {
            is SearchStates.Initial -> {
            }
            is SearchStates.Loading -> {
                LoadingIndicator()
            }
            is SearchStates.Success -> {
                ImageGrid(images = state.images)
            }
            is SearchStates.Error -> {
                ErrorMessage(message = state.message)
            }
        }
    }
}

@Composable
fun LoadingIndicator() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text( text= "Loading Images...")
    }
}

@Composable
fun ErrorMessage(message: String) {
    Text(
        text = message,
        color = Color.Red,
        modifier = Modifier.padding(16.dp)
    )
}

