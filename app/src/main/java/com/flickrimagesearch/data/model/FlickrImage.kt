package com.flickrimagesearch.data.model

data class FlickrImage(
    val title: String,
    val imageUrl: String,
    val description: String,
    val author: String,
    val publishedDate: String
)

fun FlickrItem.toFlickrImage(): FlickrImage {
    return FlickrImage(
        title = title,
        imageUrl = media.m,
        description = description,
        author = author,
        publishedDate = published
    )
}