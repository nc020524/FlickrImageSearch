package com.flickrimagesearch.data.repo

import com.flickrimagesearch.data.model.FlickrImage
import com.flickrimagesearch.data.model.toFlickrImage
import com.flickrimagesearch.data.remote.FlickrApiService

class FlickrRepository(
    private val apiService: FlickrApiService = FlickrApiService.create()
) {
    suspend fun searchImages(query: String): List<FlickrImage> {
        return apiService.searchImages(query).items.map { it.toFlickrImage() }
    }
}