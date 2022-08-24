package com.lloyd.photos.presentation

import com.lloyd.photos.data.models.PhotosResponse
import com.lloyd.photos.presentation.photos.PhotosRepository
import com.lloyd.photos.presentation.photos.PhotosViewModel
import com.ssup.composeandcleanarchitecture.domain.state.DataState
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.consumeAsFlow
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

@DisplayName("Photos View model Test cases")
@OptIn(ExperimentalCoroutinesApi::class)
class PhotoViewModelTest {
    private val photosRepository = mockk<PhotosRepository>(relaxed = true)
    private val dispatcher = StandardTestDispatcher()
    private val channel = Channel<DataState<PhotosResponse>>()
    private lateinit var photosViewModel: PhotosViewModel
    private val photosResponse = PhotosResponse()

    @BeforeEach
    fun setUp() {
        Dispatchers.setMain(dispatcher)
        photosViewModel = PhotosViewModel(photosRepository)
    }

    @DisplayName("Fetch photos - success")
    @Test
    fun photosFetchSuccessful() = runTest {
        every { photosRepository.getPhotos() } returns channel.consumeAsFlow()
        photosViewModel.getPhotos()
        channel.send(DataState.success(data = photosResponse))
        Assertions.assertEquals("", photosViewModel.error.value)
    }

    @DisplayName("Fetch photos - failure")
    @Test
    fun photosFetchFailed() = runTest {
        every { photosRepository.getPhotos() } returns channel.consumeAsFlow()
        photosViewModel.getPhotos()
        val mockErrorMessage = "Fetch failed"
        channel.send(DataState.error(mockErrorMessage))
        Assertions.assertEquals(mockErrorMessage, photosViewModel.error.value)
    }
}