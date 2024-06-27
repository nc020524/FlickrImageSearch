package com.flickrimagesearch

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.flickrimagesearch.ui.screen.FlickrSearchScreen
import com.flickrimagesearch.ui.theme.FlickrSearchTheme
import com.flickrimagesearch.ui.viewmodel.FlickrViewModel

class MainActivity : ComponentActivity() {
    private val viewModel: FlickrViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FlickrSearchTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    FlickrSearchScreen(viewModel)
                }
            }
        }
    }
}