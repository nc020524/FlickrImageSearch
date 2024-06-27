package com.flickrimagesearch.ui.viewmodel

import com.flickrimagesearch.data.model.FlickrImage
import com.flickrimagesearch.data.repo.FlickrRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.*
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.kotlin.*

@ExperimentalCoroutinesApi
class FlickrViewModelTest {

    private val testDispatcher = StandardTestDispatcher()
    private lateinit var repository: FlickrRepository
    private lateinit var viewModel: FlickrViewModel

    @Before
    fun setup() {
        Dispatchers.setMain(testDispatcher)
        repository = mock()
        viewModel = FlickrViewModel(repository)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `updateSearchQuery updates searchQuery and triggers search`() = runTest {
        val testQuery = "test"
        val testResults = listOf(
            FlickrImage("Test 1", "url1", "desc1", "author1", "date1"),
            FlickrImage("Test 2", "url2", "desc2", "author2", "date2")
        )

        whenever(repository.searchImages(testQuery)).thenReturn(testResults)

        viewModel.updateSearchQuery(testQuery)
        testDispatcher.scheduler.advanceUntilIdle()

        assertEquals(testQuery, viewModel.searchQuery.value)
        assert(viewModel.uiState.value is SearchStates.Success)
        assertEquals(testResults, (viewModel.uiState.value as SearchStates.Success).images)
        verify(repository).searchImages(testQuery)
    }
}