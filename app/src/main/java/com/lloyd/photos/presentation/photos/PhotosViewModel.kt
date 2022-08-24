package com.lloyd.photos.presentation.photos

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lloyd.photos.data.models.PhotosResponseItem
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class PhotosViewModel @Inject constructor(private val photosRepository: PhotosRepository) :
    ViewModel() { //Inject context via Dagger Hilt

    val photos: MutableState<List<PhotosResponseItem>> = mutableStateOf(emptyList())
    val error = mutableStateOf("")

    fun getPhotos() {
        photosRepository.getPhotos().onEach { response ->
            response.data?.let { photos.value = response.data }
            response.error?.let {
                error.value = it
            }
        }.launchIn(viewModelScope)
    }
}