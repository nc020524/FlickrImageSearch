package com.flickrimagesearch.ui.viewmodel

import com.flickrimagesearch.data.model.FlickrImage

sealed class SearchStates {
    data object Initial : SearchStates()
    data object Loading : SearchStates()
    data class Success(val images: List<FlickrImage>) : SearchStates()
    data class Error(val message: String) : SearchStates()
}