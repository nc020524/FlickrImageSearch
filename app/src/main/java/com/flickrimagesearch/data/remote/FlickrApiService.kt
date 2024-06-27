package com.flickrimagesearch.data.remote

import com.flickrimagesearch.data.model.FlickrResponse
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface FlickrApiService {
    @GET("photos_public.gne")
    suspend fun searchImages(
        @Query("tags") tags: String,
        @Query("format") format: String = "json",
        @Query("nojsoncallback") noJsonCallback: Int = 1
    ): FlickrResponse

    companion object {
        private const val BASE_URL = "https://api.flickr.com/services/feeds/"

        fun create(): FlickrApiService {
            val retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()

            return retrofit.create(FlickrApiService::class.java)
        }
    }
}
