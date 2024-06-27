package com.flickrimagesearch.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.flickrimagesearch.data.repo.FlickrRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class FlickrViewModel(
    private val repository: FlickrRepository = FlickrRepository()
) : ViewModel() {
    private val _uiState = MutableStateFlow<SearchStates>(SearchStates.Initial)
    val uiState: StateFlow<SearchStates> = _uiState.asStateFlow()

    private val _searchQuery = MutableStateFlow("")
    val searchQuery: StateFlow<String> = _searchQuery.asStateFlow()

    fun updateSearchQuery(query: String) {
        _searchQuery.value = query
        searchImages()
    }

    private fun searchImages() {
        viewModelScope.launch {
            _uiState.value = SearchStates.Loading
            try {
                val results = repository.searchImages(_searchQuery.value)
                _uiState.value = SearchStates.Success(results)
            } catch (e: Exception) {
                _uiState.value = SearchStates.Error("Failed to search images: ${e.localizedMessage}")
            }
        }
    }
}